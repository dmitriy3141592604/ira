package sql;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static utils.Value.newValue;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;

import ch.qos.logback.core.spi.ContextAwareBase;
import utils.Value;

public class SelectTest extends ContextAwareBase {

	public static class SqlParser {

		public String getTableName(String sql) {
			final ArrayList<String> words = getWords(sql);
			return words.get(words.indexOf("from") + 1);
		}

		public List<String> getColumnNames(String sql) {
			final ArrayList<String> words = getWords(sql);
			final int startIndex = words.indexOf("select") + 1;
			final int stopIndex = words.indexOf("from");
			return words.subList(startIndex, stopIndex);
		}

		private ArrayList<String> getWords(String sql) {
			final String[] split = sql.toLowerCase().replace(',', ' ').split("\\s+");
			return new ArrayList<>(Arrays.asList(split));
		}

	}

	@Test
	public void test$00() throws Exception {

		final String query = "select login, city, zip from other_table as st";
		{

			final SqlParser parser = new SqlParser();
			final String tableName = parser.getTableName(query);

			final Comparator<List<String>> bodyComparator = new Comparator<List<String>>() {

				@Override
				public int compare(List<String> o1, List<String> o2) {
					for (int i = 0; i < o1.size(); ++i) {
						final int cmpValue = o1.get(i).compareTo(o2.get(i));
						if (0 != cmpValue) {
							return cmpValue;
						}
					}
					return 0;
				}
			};

			final Set<List<String>> body = new TreeSet<>(bodyComparator);
			{
				final InputStream stream = getClass().getClassLoader().getResourceAsStream("sql/" + tableName + ".tbl");
				assertNotNull(stream);

				final Map<String, Integer> header = new LinkedHashMap<>();

				try (final BufferedReader br = new BufferedReader(new InputStreamReader(stream));) {
					final String headers = br.readLine();
					final String[] split = headers.split("\\t");
					for (int i = 0; i < split.length; ++i) {
						final String headerItem = split[i];
						if (header.containsKey(headerItem)) {
							throw new IllegalStateException();
						}
						header.put(headerItem.toLowerCase(), i);
					}

					String row;
					final int headersCount = header.size();
					while (null != (row = br.readLine())) {
						row = row.trim();
						if (row.startsWith("#")) {
							continue;
						}
						final String[] rowItems = row.split("\\t");
						if (rowItems.length != headersCount) {
							throw new IllegalStateException();
						}
						final ArrayList<String> rowList = new ArrayList<>();
						for (int i = 0; i < rowItems.length; ++i) {
							rowList.add(rowItems[i]);
						}
						body.add(rowList);
					}

				}

				final List<String> columnNames = parser.getColumnNames(query);

				final int[] expectedColumns = new int[columnNames.size()];
				int index = 0;
				for (final String s : columnNames) {
					expectedColumns[index++] = header.get(s);
				}

				final Set<List<String>> resultTable = new TreeSet<>(bodyComparator);
				{
					for (final List<String> row : body) {
						final ArrayList<String> newRow = new ArrayList<>();
						for (int i = 0; i < expectedColumns.length; ++i) {
							newRow.add(row.get(expectedColumns[i]));
						}
						resultTable.add(newRow);
					}
				}

				final Value<String> value = newValue();
				{
					resultTable.forEach(row -> row.forEach(c -> value.setValue(value.getValue() + "|" + row)));
				}
				final String expected = "null|[alisa, Msk, 456789]|[alisa, Msk, 456789]|[alisa, Msk, 456789]|[bob, Msk, 156789]|[bob, Msk, 156789]|[bob, Msk, 156789]";
				assertEquals(expected, value.getValue());

			}

		}
	}

}

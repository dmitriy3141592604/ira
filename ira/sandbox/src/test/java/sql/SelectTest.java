package sql;

import static org.junit.Assert.assertNotNull;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

import ch.qos.logback.core.spi.ContextAwareBase;

public class SelectTest extends ContextAwareBase {

	@Test
	public void test$00() throws Exception {

		final String query = "select login from other_table as st";
		{
			final String sql = query;
			final String sp0 = "\\s*";
			final String sp1 = "\\s+";
			final String word = "\\w+";
			final Pattern p = Pattern.compile(
					"^" + sp0 + "select" + sp1 + "(" + word + ")" + sp1 + "from" + sp1 + "(" + word + ")" + sp1 + "as" + sp1 + "(" + word + ")");
			final Matcher m = p.matcher(sql);

			final boolean matches = m.matches();
			// assertEquals(true, matches);

			final String group1 = m.group(1);
			final String group2 = m.group(2);
			final String group3 = m.group(3);

			// assertEquals("value", group1.toLowerCase());
			// assertEquals("simple_table", group2);
			// assertEquals("st", group3);

			final String tableName = group2;
			final String columnName = group1;

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

				// final String string = IOUtils.toString(stream);

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

				final int[] expectedColumns = new int[] { header.get(columnName) };

				// Execute query
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

				// assertEquals(1, expectedColumns[0]);
				// assertEquals(new Integer(0), header.get("name"));
				// assertEquals(new Integer(1), header.get("value"));
				// assertEquals("[name, value, a]", header.keySet().toString());

				{
					for (final List<String> row : resultTable) {

						for (final String col : row) {
							System.out.println(row + "\t");
						}
					}
				}

			}

		}
	}

}

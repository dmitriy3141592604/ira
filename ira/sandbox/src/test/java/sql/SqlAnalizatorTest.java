package sql;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.junit.Test;

import sql.SqlLexerTest.TokenType;

public class SqlAnalizatorTest {

	public static class Query {

		private SelectSection selectSection;

		@Override
		public String toString() {
			return "query[" + selectSection + "]";
		}

		public SelectSection getSelectSection() {
			return selectSection;
		}

		public void setSelectSection(SelectSection selectSection) {
			this.selectSection = selectSection;
		}
	}

	public static class SelectSection {
		private FieldList fieldList;

		@Override
		public String toString() {
			return "select";
		}

		public FieldList getFieldList() {
			return fieldList;
		}

		public void setFieldList(FieldList fieldList) {
			this.fieldList = fieldList;
		}
	}

	public static class FieldList {

		private List<OneFieldItem> items;

	}

	public static class OneFieldItem {
		private FieldRef fieldRef;
		private FieldRefAlias fieldRefAlias;

	}

	public static class FieldRef {
		private Token tableAlias;
		private Token fieldName;

	}

	public static class FieldRefAlias {
		private Token value;

	}

	@Test
	public void test$parseLevel1() {
		assertEquals("query[select[id:a_AS_id:c]]", new SqlAnalizator().parse(new SqlLexer().parse("select a.b as c")).toString());
	}

	public static class SqlAnalizator {

		public Query parse(List<Token> tokens) {
			return query(tokens.listIterator());
		}

		private Query query(ListIterator<Token> iterator) {
			final Query query = new Query();
			final SelectSection sc = selectSection(iterator);
			query.setSelectSection(sc);
			return query;
		}

		private SelectSection selectSection(ListIterator<Token> iterator) {
			if (!iterator.hasNext()) {
				throw new RuntimeException("Unexpected end");
			}
			final Token next = iterator.next();
			if (!next.getType().equals(TokenType.SELECT)) {
				throw new RuntimeException("Expected select");
			}
			final SelectSection selectSection = new SelectSection();
			final FieldList fs = fieldList(iterator);
			selectSection.setFieldList(fs);
			return selectSection;

		}

		private FieldList fieldList(ListIterator<Token> iterator) {

			if (!iterator.hasNext()) {
				return new FieldList();
			}
			final ArrayList<OneFieldItem> arrayList = new ArrayList<>();

			final Token next = iterator.next();
			if (TokenType.IDENTIFIER.equals(next.getType())) {

			} else {
				iterator.previous();
			}
			final FieldList fl = new FieldList();
			// fl.set
			return fl;
		}
	}

}

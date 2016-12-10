package sandbox.sql.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import sandbox.sql.SqlString;
import sandbox.sql.SqlValueComparator;

public class SqlStringTest {

	private SqlValueComparator comparator;
	private SqlString one;
	private SqlString other;

	@Before
	public final void setUpSqlStringTest() {
		comparator = new SqlValueComparator();
		one = new SqlString("one");
		other = new SqlString("other");
	}

	@Test
	public void test$equals() {
		assertEquals(true, comparator.compare(one, one));
	}

	@Test
	public void test$motEquals() {
		assertEquals(false, comparator.compare(one, other));
	}

}

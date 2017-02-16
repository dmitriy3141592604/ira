package utils;

import static junit.framework.TestCase.assertEquals;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

/**
 * Author: timofeevdv Date: 03.02.2017
 */
public class OneWayListTest {

	private OneWayList<String> list;

	@Before
	public void setUp() {
		list = new OneWayList<String>();
	}

	@Test
	public void testAddElement() {
		list.add("1");
		list.add("2");
		list.add("3");
	}

	@Test
	public void testInsertAtCorrectPosition() {
		list.add("1");
		list.add("2");
		list.add("3");
		list.insert("1.5", 1);
		assertEquals("1", list.get(0));
		assertEquals("1.5", list.get(1));
		assertEquals("2", list.get(2));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInsertAtNull() {
		list.insert("1", null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInsertAtIncorrectPosition() {
		list.add("1");
		list.add("2");
		list.add("3");
		list.insert("1.5", 10);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInsertAtNegativePosition() {
		list.add("1");
		list.add("2");
		list.add("3");
		list.insert("1.5", -1);
	}

	@Test
	public void testCorrectSize() {
		list.add("1");
		list.add("2");
		list.add("3");
		assertEquals(new Integer(3), list.getSize());
	}

	@Test
	public void testNext() {
		list.add("1");
		final Iterator<String> i = list.listIterator();
		assertEquals("1", i.next());
	}

	@Test
	public void testIteratorHasNext() {
		list.add("1");
		assertEquals(true, list.listIterator().hasNext());
	}

	@Test
	public void testIteratorIncreasePos() {
		list.add("1");
		list.add("2");
		final Iterator<String> i = list.listIterator();
		assertEquals("1", i.next());
		assertEquals("2", i.next());
	}

	@Test
	public void testIteratorInsert() {
		list.add("1");
		list.add("2");
		final OneWayList<String>.ListItr i = list.listIterator();
		i.next();
		i.insert("1.5");
		assertEquals("1.5", i.next());
	}

	@Test
	public void testNoNextOnEmptyList() {
		final OneWayList<String>.ListItr i = list.listIterator();
		assertEquals(false, i.hasNext());
	}

	@Test
	public void testHasNext() {
		final OneWayList<String>.ListItr i = list.listIterator();
		i.insert("1");
		assertEquals(true, i.hasNext());
	}

	@Test
	public void testIteratorInsertOnEmptyList() {
		final OneWayList<String>.ListItr i = list.listIterator();
		i.insert("1");
		assertEquals("1", i.next());
	}
}

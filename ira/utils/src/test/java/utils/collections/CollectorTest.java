package utils.collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static utils.collections.Collector.newCollector;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import testutils.RandomizedTest;

@RunWith(MockitoJUnitRunner.class)
public class CollectorTest implements RandomizedTest {

	private Collection<String> storage;

	private Collector<String> collector;

	@Before
	public final void setUpCollectorTest() {
		collector = Collector.newCollector(storage = new ArrayList<String>());
	}

	@Test
	public void test$remember() {
		final String newItem = randomString();
		assertSame(newItem, collector.remember(newItem));
	}

	@Test
	public void test$getStorage() {
		assertSame(storage, collector.getStorage());
	}

	@Test
	@SuppressWarnings("unchecked")
	public void test$iteratorReturnFromStorage() {
		final Collection<String> mc = mock(Collection.class);
		final Iterator<String> i = mock(Iterator.class);
		when(mc.iterator()).thenReturn(i);

		assertSame(i, newCollector(mc).iterator());
	}

	@Test
	public void test$iterable() {
		final String rs = randomString();
		collector.remember(rs);
		final Iterator<String> iterator = collector.iterator();
		assertEquals(rs + "|false", iterator.next() + "|" + iterator.hasNext());
	}

}

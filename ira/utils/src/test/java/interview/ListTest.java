package interview;

import static org.junit.Assert.assertEquals;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

public class ListTest {

	public static class Person {

		private final String name;

		private Integer age;

		public Person(String name) {
			this.name = name;
		}

		public Integer getAge() {
			return age;
		}

		public void setAge(Integer age) {
			this.age = age;
		}

		public String getName() {
			return name;
		}

	}

	public static class FixedList {

		private final int[] items;

		public FixedList(int[] items) {
			this.items = items;
		}

		public Iterator<Integer> iterator() {
			final int[] indexes = new int[0];
			indexes[0] = 0;
			return new Iterator<Integer>() {

				@Override
				public boolean hasNext() {
					return indexes[0] < items.length;
				}

				@Override
				public Integer next() {
					return items[indexes[0]++];
				}

			};
		}
	}

	@Test
	public void test$001() {
		final List<Person> l = new LinkedList<>();
		l.add(new Person("alisa"));
		l.add(new Person("bob"));
		l.add(new Person("alisa"));

		assertEquals("alisa.bob.alisa.", content(l));

		final Iterator<Person> i1 = l.iterator();

		final Iterator<Person> i2 = l.iterator();

		/**
		 * <code> <sample>
		int t = 3;
		final int v = 5;
		"Hello, World".substring(t++, v);
		</code>
		 **/

		final Person ip1 = i1.next();
		assertEquals("alisa", ip1.getName());

		final Person ip2 = i2.next();
		assertEquals("alisa", ip2.getName());

		i1.remove();

		assertEquals("bob.alisa.", content(l));

		// final boolean hasNext = i2.hasNext();
		// assertEquals(false, hasNext);

		final Person maybeBob = i2.next();
		assertEquals("bob", maybeBob.getName()); // Unreshable Code

	}

	private String content(List<Person> l) {
		final StringBuilder sb = new StringBuilder();
		for (int i = 0; i < l.size(); ++i) {
			sb.append(l.get(i).getName()).append(".");
		}
		return sb.toString();
	}

}

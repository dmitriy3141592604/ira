package utils.TMP;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Consumer;

import org.junit.Test;

/**
 * TODO Восстановить описания теста. Забыл, что вообще делали.
 *
 * @author fdv.741
 *
 */
public class TempContract extends TempContractBase {

	public static class Holder {

		public static String publicValue = "";

		public static List<String> messages = new LinkedList<String>();
	}

	public static class BaseContract<T> {

		public T marker;

		public Map<String, Consumer<T>> asserts = new TreeMap<String, Consumer<T>>();

		protected void $(String name, Consumer<T> consumer) {
			asserts.put(name, consumer);
		}
	}

	public static class ContractSample extends BaseContract<Integer> {
		{
			$("������ �����������", value -> Holder.messages.add("" + marker + "#" + "������: " + value));

			$("������ �����������2", value -> {
				Holder.messages.add("" + marker + "#" + "������: " + value);
				Holder.messages.add("" + marker + "#" + "������: " + value);
			});
		}
	}

	@Test
	public void test1() {
		Holder.publicValue = "Hello";
		final ContractSample cs = new ContractSample();
		cs.marker = 1;

		cs.asserts.get("������ �����������").accept(cs.marker);

		assertEquals("[1#������: 1]", Holder.messages.toString());
	}

}

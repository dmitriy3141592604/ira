package utils.TMP;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Consumer;

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
			$("Другое ограничение", value -> Holder.messages.add("" + marker + "#" + "Другое: " + value));

			$("Другое ограничение2", value -> {
				Holder.messages.add("" + marker + "#" + "Другое: " + value);
				Holder.messages.add("" + marker + "#" + "Другое: " + value);
			});
		}
	}

	@Test
	public void test1() {
		Holder.publicValue = "Hello";
		final ContractSample cs = new ContractSample();
		cs.marker = 1;

		cs.asserts.get("Другое ограничение").accept(cs.marker);

		assertEquals("[1#Другое: 1]", Holder.messages.toString());
	}

}

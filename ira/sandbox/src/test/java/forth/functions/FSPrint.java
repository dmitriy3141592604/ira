package forth.functions;

import forth.FOperation;
import forth.FToken;

public class FSPrint extends FOperation {
	public FSPrint() {
		super(stack -> {
			final FToken pop = stack.pop();
			System.out.println(pop);
		});
	}
}

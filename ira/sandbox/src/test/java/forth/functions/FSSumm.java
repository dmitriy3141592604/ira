package forth.functions;

import forth.FOperation;
import forth.FToken;

public class FSSumm extends FOperation {

	public FSSumm() {
		super(stack -> {
			final FToken top = stack.pop();
			final FToken next = stack.pop();
			stack.push(next.summ(top));
		});
	}
}

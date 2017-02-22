package forth.functions;

import org.springframework.stereotype.Component;

import forth.FOperation;
import forth.FToken;

@Component
public class FSSwap extends FOperation {
	public FSSwap() {
		super(stack -> {
			final FToken pop1 = stack.pop();
			final FToken pop2 = stack.pop();
			stack.push(pop1);
			stack.push(pop2);
		});
	}
}

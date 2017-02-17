package forth.functions;

import org.springframework.stereotype.Component;

import forth.FOperation;
import forth.FToken;

@Component
public class FSDup extends FOperation {
	public FSDup() {
		super(stack -> {
			final FToken pop = stack.pop();
			stack.push(pop);
			stack.push(pop);
		});
	}
}

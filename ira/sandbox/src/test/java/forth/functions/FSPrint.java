package forth.functions;

import org.springframework.stereotype.Component;

import forth.FOperation;
import forth.FToken;

@Component
public class FSPrint extends FOperation {
	public FSPrint() {
		super(stack -> {
			final FToken pop = stack.pop();
			System.out.println(pop);
		});
	}
}

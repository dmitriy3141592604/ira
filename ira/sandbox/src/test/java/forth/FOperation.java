package forth;

import java.util.function.Consumer;

// TODO Rename to FSOperation
public class FOperation extends FToken {

	private final Consumer<FStack> impl;

	public FOperation(Consumer<FStack> impl) {
		this.impl = impl;
	}

	@Override
	public void applay(FStack stack) {
		impl.accept(stack);
	}

	@Override
	public FToken summ(FToken top) {
		throw new UnsupportedOperationException();
	}

	@Override
	public FToken summ(FString top) {
		throw new UnsupportedOperationException();
	}

	@Override
	public FToken summ(FInteger top) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String stringValue() {
		throw new UnsupportedOperationException();
	}

}
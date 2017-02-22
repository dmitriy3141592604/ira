package forth;

public class FInteger extends FToken {

	private final Integer impl;

	public FInteger(Integer impl) {
		this.impl = impl;
	}

	@Override
	public void applay(FStack stack) {
		stack.push(this);
	}

	@Override
	public FToken summ(FToken top) {
		return top.summ(this);
	}

	@Override
	public FToken summ(FString top) {
		throw new UnsupportedOperationException();
	}

	@Override
	public FToken summ(FInteger top) {
		return new FInteger(top.impl + this.impl);
	}

	@Override
	public String toString() {
		return impl.toString();
	}

	@Override
	public String stringValue() {
		throw new UnsupportedOperationException();
	}

}
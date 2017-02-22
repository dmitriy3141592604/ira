package forth;

public class FString extends FToken {

	private final String string;

	public FString(String string) {
		this.string = string;
	}

	@Override
	public String toString() {
		return string;
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
		return new FString(top.string + "" + this.string);
	}

	@Override
	public FToken summ(FInteger top) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String stringValue() {
		return string;
	}

}
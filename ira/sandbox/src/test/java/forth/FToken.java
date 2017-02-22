package forth;

public abstract class FToken {

	public abstract void applay(FStack stack);

	public abstract FToken summ(FToken top);

	public abstract FToken summ(FInteger top);

	public abstract FToken summ(FString top);

	// FIXME Remove this.
	public abstract String stringValue();

}
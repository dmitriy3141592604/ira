package sandbox;

public class SqlString implements SqlValue {

	private final String value;

	public SqlString(String value) {
		this.value = value;
	}

	@Override
	public boolean compare(SqlValue right) {
		return right.compare(this);
	}

	@Override
	public boolean compare(SqlString left) {
		return left.getValue().equals(value);
	}

	private String getValue() {
		return value;
	}

}

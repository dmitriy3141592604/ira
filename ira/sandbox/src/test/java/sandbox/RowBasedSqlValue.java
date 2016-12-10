package sandbox;

public class RowBasedSqlValue implements SqlValue {

	public RowBasedSqlValue(SqlRow row, int index) {

	}

	@Override
	public boolean compare(SqlValue right) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean compare(SqlString left) {
		throw new UnsupportedOperationException();
	}

}

package sandbox;

import java.util.List;

public class SqlRow {

	@SuppressWarnings("unused")
	private final List<SqlValue> impl;

	public SqlRow(List<SqlValue> row) {
		this.impl = row;
	}

}

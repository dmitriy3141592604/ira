package sandbox.sql;

public interface SqlValue {

	boolean compare(SqlValue right);

	boolean compare(SqlString left);

}

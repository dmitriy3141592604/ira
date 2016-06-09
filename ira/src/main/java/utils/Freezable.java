package utils;

public interface Freezable {

	public void freeze(String lockName);

	public void unfreeze(String lockName);

}

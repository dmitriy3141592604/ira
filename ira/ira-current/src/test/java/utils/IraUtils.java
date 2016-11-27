package utils;

import java.lang.reflect.Proxy;

public class IraUtils {

	private static final String ALL_FIELDS = "allFields";

	public static void freeze(Object freezeCandidate) {
		String allFields = ALL_FIELDS;
		freeze(freezeCandidate, allFields);
	}

	public static void freeze(Object freezeCandidate, String lockName) {
		((Freezable) Proxy.getInvocationHandler(freezeCandidate)).freeze(lockName);
	}

	public static void unfreeze(Object freezeCandidate) {
		String allFields = ALL_FIELDS;
		unfreeze(freezeCandidate, allFields);
	}

	public static void unfreeze(Object freezeCandidate, String lockName) {
		((Freezable) Proxy.getInvocationHandler(freezeCandidate)).unfreeze(lockName);
	}

}

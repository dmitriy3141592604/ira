package structure;

public class MarkerException extends RuntimeException {

	private static final long serialVersionUID = -6819884698081561201L;

	public static class PreviousMarkerHasDifferentTypeException extends MarkerException {

		private static final long serialVersionUID = 349972674795056834L;

	}

	public static class PreviousMarkerValueRewriteException extends MarkerException {

		private static final long serialVersionUID = 5385704816946132061L;

	}

}
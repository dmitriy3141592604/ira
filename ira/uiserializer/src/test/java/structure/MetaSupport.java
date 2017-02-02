package structure;

import java.util.Map;
import java.util.TreeMap;

import structure.MarkerException.PreviousMarkerHasDifferentTypeException;
import structure.MarkerException.PreviousMarkerValueRewriteException;

public class MetaSupport {

	private static final Object DEFAULT_MARKER = Boolean.TRUE;

	private final Map<String, Object> markers = new TreeMap<String, Object>();

	public boolean hasMarker(String string) {
		return null != markers.get(string);
	}

	public void markWith(String marker, Object value) {
		markWith(marker, value, 1);
	}

	public void mark(String marker) {
		markWith(marker, DEFAULT_MARKER, 1);
	}

	@SuppressWarnings("unchecked")
	public <T> T getMarker(String marker, T defaultValue) {
		final Object v = markers.get(marker);
		if (null != v) {
			if (v.getClass().equals(defaultValue.getClass())) {
				return (T) v;
			}
			throw new PreviousMarkerHasDifferentTypeException();
		}
		return defaultValue;
	}

	protected void markWith(String marker, Object value, int correction) {
		final Object v = markers.get(marker);
		if (v != null && !v.equals(value)) {
			throw new PreviousMarkerValueRewriteException();
		}
		if (value == null) {
			throw new NullPointerException();
		}
		markers.put(marker, value);
	}

}
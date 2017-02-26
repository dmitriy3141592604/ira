package model;

import java.util.Map;
import java.util.TreeMap;

import model.MarkerException.PreviousMarkerHasDifferentTypeException;
import model.MarkerException.PreviousMarkerValueRewriteException;
import utils.Responsibility;

/** Класс предоставляет методы работы с коллекцией маркеров и механизм ограничений для выполняемых операций. **/
@Responsibility("Обеспечивает управление набором маркеров")
public class MarkerSupport {

	private static final Object DEFAULT_MARKER = Boolean.TRUE;

	private final Map<String, Object> markers = new TreeMap<String, Object>();

	public boolean hasMarker(String string) {
		return null != markers.get(string);
	}

	// FIXME Протестировать
	public boolean hasMarker(Enum<?> enumMarker) {
		return hasMarker(enumMarker.toString());
	}

	public void markWith(String marker, Object value) {
		markWithImpl(marker, value);
	}

	// FIXME Протестировать
	public void markWith(Enum<?> marker, Object value) {
		markWith(marker.toString(), value);
	}

	public void mark(String marker) {
		markWithImpl(marker, DEFAULT_MARKER);
	}

	// FIXME Протестировать
	public void mark(Enum<?> marker) {
		mark(marker.toString());
	}

	@SuppressWarnings("unchecked")
	public <T> T getMarkerValue(String marker, T defaultValue) {
		final Object v = markers.get(marker);
		if (null != v) {
			if (v.getClass().equals(defaultValue.getClass())) {
				return (T) v;
			}
			throw new PreviousMarkerHasDifferentTypeException();
		}
		return defaultValue;
	}

	private void markWithImpl(String marker, Object value) {
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
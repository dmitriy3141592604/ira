package structure;

import java.util.Map;
import java.util.TreeMap;

public class MetaSupport {

	private static final Object PRESENT_MARKER = new Object();

	private final Map<String, Object> metas = new TreeMap<String, Object>();

	public void annotate(String marker, Object value) {
		final Object v = metas.get(marker);
		if (v != null && !v.equals(value)) {
			throw new IllegalStateException("Meta present with previousValue");
		}
		metas.put(marker, value);
	}

	public void annotate(String marker) {
		metas.put(marker, PRESENT_MARKER);
	}

	public boolean hasMeta(String string) {
		return null != metas.get(string);
	}

	@SuppressWarnings("unchecked")
	public <T> T meta(String marker, T defaultValue) {
		final Object v = metas.get(marker);
		if (null != v) {
			return (T) v;
		}
		return defaultValue;
	}

}
package test.uibuilder;

class IndentResultConstructorHelper {

	private final StringBuilder sb = new StringBuilder();

	private final String eol = "\n";

	public void $(String v) {
		n("", v);
	}

	public void $$(String v) {
		n(" ", v);
	}

	public void $$$(String v) {
		n("  ", v);
	}

	public void $$$$(String v) {
		n("   ", v);
	}

	public void $$$$$(String v) {
		n("    ", v);
	}

	private StringBuilder n(String preifx, String v) {
		return sb.append(preifx).append(v).append(eol);
	}

	public String getLog() {
		return sb.toString();
	}
}
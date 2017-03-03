package grid;

public class Cell {
	private int colspan = 1;
	private int rowspan = 1;
	private String content = "";

	public Cell(int colspan0, int rowspan0, String content) {
		this.colspan = colspan0 + 1;
		this.rowspan = rowspan0 + 1;
		this.content = content;
	}

	public Cell(String content) {
		this(0, 0, content);
	}

	public int getColspan() {
		return colspan;
	}

	public void setColspan(int colspan) {
		this.colspan = colspan;
	}

	public int getRowspan() {
		return rowspan;
	}

	public void setRowspan(int rowspan) {
		this.rowspan = rowspan;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
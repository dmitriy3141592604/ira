package sequece;

import java.awt.Graphics2D;

public class LineCursor {

	private final Graphics2D g;

	private int x = 0;

	private int y = 0;

	private int scrollX = 0;

	private int scrollY = 0;

	public LineCursor(Graphics2D g) {
		this.g = g;
	}

	public LineCursor move(int x, int y) {
		this.x = x;
		this.y = y;
		return this;
	}

	public LineCursor shift(int width, int height) {
		final int nextX = x + width;
		final int nextY = y + height;
		g.drawLine(scrollX + x, scrollY + y, scrollX + nextX, scrollY + nextY);
		this.x = nextX;
		this.y = nextY;
		return this;
	}

	public LineCursor scroll(int deltaX, int deltaY) {
		this.scrollX += deltaX;
		this.scrollY += deltaY;
		return this;
	}

}
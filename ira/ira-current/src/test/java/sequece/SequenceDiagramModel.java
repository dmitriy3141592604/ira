package sequece;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

class SequenceDiagramModel {
	static class RowModel {
		String sender;
		String message;
		String receiver;

		public RowModel(String sender, String message, String receiver) {
			this.sender = sender;
			this.message = message;
			this.receiver = receiver;
		}

		static RowModel newRowModel(String sender, String message, String receiver) {
			return new RowModel(sender, message, receiver);
		}

	}

	private final List<String> objects = new ArrayList<String>();
	private final List<RowModel> arrows = new ArrayList<RowModel>();

	public void addObject(String name) {
		objects.add(name);
	}

	void exchange(String sender, String message, String receiver) {
		arrows.add(RowModel.newRowModel(sender, message, receiver));
	}

	public BufferedImage generate() {
		final int width = 500;
		final int height = 500;
		final BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

		final Graphics2D g = bufferedImage.createGraphics();
		final Font font = new Font("Serif", Font.PLAIN, 30);
		g.setFont(font);
		g.setColor(Color.black);

		build(g);

		return bufferedImage;

	}

	public static class LineCursor {

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

	private void build(Graphics2D g) {
		final LineCursor cursor = new LineCursor(g);

		final Dimension textDimension = textDimension(g, "Alisa");
		final int height = (int) textDimension.getHeight();
		final int width = (int) textDimension.getWidth();
		cursor.move(0, 0);
		cursor.scroll(0, 10);
		g.drawString("Alisa", 0, height);
		cursor.shift(width, 0).shift(0, height).shift(-width, 0).shift(0, -height);
		cursor.scroll(100, 0);
		cursor.shift(width, 0).shift(0, height).shift(-width, 0).shift(0, -height);
	}

	public Dimension textDimension(Graphics2D g, String text) {
		final FontMetrics metric = g.getFontMetrics();
		final int height = metric.getHeight();
		final int width = metric.stringWidth(text);
		return new Dimension(width, height);
	}

	@SuppressWarnings("unused")
	private void drawLabel(Graphics2D g) {

		final String str = "Здравствуй, Мир!";
		final FontMetrics metric = g.getFontMetrics();
		final int width = metric.stringWidth(str);
		final int height = metric.getHeight();
		g.setColor(Color.black);

		final int x = 150;
		final int y = 80;

		g.drawString(str, x, y);
		g.drawRect(x - 5, y + 10 - height, width + 10, height - 2);
	}
}
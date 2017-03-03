package grid;

public class LazyMatrix<T> {

	interface LazyMatrixIterator<V> {
		void accept(int x, int y, V value, boolean isRowStart, boolean isRowEnd);
	}

	private final int width;

	private final int height;

	private final Object[][] impl;

	public LazyMatrix(int width, int height) {
		this.width = width;
		this.height = height;
		impl = new Object[this.width][this.height];
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	@SuppressWarnings("unchecked")
	public void iterateVertical(LazyMatrixIterator<T> callback) {
		for (int y = 0; y < width; ++y) {
			for (int x = 0; x < width; ++x) {
				callback.accept(x, y, (T) impl[x][y], x == 0, x == width - 1);
			}
		}
	}

	public void assign(int x, int y, T value) {
		impl[x][y] = value;
	}

}

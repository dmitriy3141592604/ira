package vector;

public class Vector {

	private static final int xIdx = 0;

	private static final int yIdx = 1;

	private static final int zIdx = 2;

	private final double impl[] = new double[] { 0, 0, 0 };

	public static Vector newVector(double x, double y, double z) {
		return new Vector(x, y, z);
	}

	public static Vector newVector(double x, double y) {
		return newVector(x, y, 0);
	}

	public Vector(double dX, double dY, double dZ) {
		impl[xIdx] = dX;
		impl[yIdx] = dY;
		impl[zIdx] = dZ;
	}

	private Vector() {
		this(0, 0, 0);
	}

	public double getX() {
		return impl[xIdx];
	}

	public double getY() {
		return impl[yIdx];
	}

	public double getZ() {
		return impl[zIdx];
	}

	@Override
	public String toString() {
		return "[" + getX() + "," + getY() + "," + getZ() + "]";
	}

	public Vector sum(Vector vector) {
		final Vector retVal = new Vector();
		for (int i = 0; i < 3; ++i) {
			retVal.impl[i] = this.impl[i] + vector.impl[i];
		}
		return retVal;
	}

	@Override
	public boolean equals(Object right_) {
		final Vector right = (Vector) right_;
		boolean equals = true;
		for (int i = 0; i < 3; ++i) {
			equals &= this.impl[i] == right.impl[i];
		}

		return equals;
	}

	public Vector mult(double mult) {
		final Vector retVal = new Vector();
		for (int i = 0; i < 3; i++) {
			retVal.impl[i] = this.impl[i] * mult;
		}
		return retVal;
	}

	public double length() {
		double qSumm = 0;
		for (int i = 0; i < 3; ++i) {
			qSumm += impl[i] * impl[i];
		}
		return Math.sqrt(qSumm);
	}

}

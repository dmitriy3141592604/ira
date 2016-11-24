package utils;

public class Tuple<F, S> {

	private final F fst;

	private final S snd;

	public <D> Tuple(F fst, S snd, D f) {
		this(fst, snd);
	}

	public Tuple(F fst, S snd) {
		this.fst = fst;
		this.snd = snd;
	}

	public F getFst() {
		return fst;
	}

	public S getSnd() {
		return snd;
	}

	public static <FF, SS> Tuple<FF, SS> newTuple(FF name, SS consumer) {
		return new Tuple<FF, SS>(name, consumer);
	}
}

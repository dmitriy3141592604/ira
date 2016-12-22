package utils;

public class Tuple<F, S> {

	private final F fst;

	private final S snd;

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

	public static <FF, SS> Tuple<FF, SS> newTuple(FF fst, SS snd) {
		return new Tuple<FF, SS>(fst, snd);
	}
}

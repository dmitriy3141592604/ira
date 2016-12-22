package utils;

public class Triple<F, S, T> {

	private final F fst;

	private final S snd;

	private final T third;

	public Triple(F fst, S snd, T third) {
		this.fst = fst;
		this.snd = snd;
		this.third = third;
	}

	public F getFst() {
		return fst;
	}

	public S getSnd() {
		return snd;
	}

	public T getThird() {
		return third;
	}

	public static <FF, SS, TT> Triple<FF, SS, TT> newTriple(FF fst, SS snd, TT third) {
		return new Triple<FF, SS, TT>(fst, snd, third);
	}

}

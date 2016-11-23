package formatting;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FunnyVariables {

	@SuppressWarnings("unused")
	@Test
	public void tryIt() {
		final String ＿ = "";
		final String ︳ = "";
		final String ¤ = "";
		final String ㅐ = "";
		final String ㅿ = "";
		final String ꗐ = "";
		assertEquals("", ＿);
		assertEquals("", ¤);
	}

	public static void main(String... args) {
		int count = 20;
		for (int i = Character.MIN_CODE_POINT; i <= Character.MAX_CODE_POINT; i++) {
			if (Character.isJavaIdentifierStart(i)) {
				System.out.print((char) i + " ");
				count--;
				if (count <= 0) {
					System.out.println();
					count = 20;
				}
			}
		}
	}

}

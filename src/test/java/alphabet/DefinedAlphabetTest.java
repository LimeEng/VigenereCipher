package alphabet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

public class DefinedAlphabetTest {

	private static final String randomAlphabet = "😀😇🤠😪👾💀👨🤜🏻☂️😂😂😂😂😂😘😘😘😘😘❤️❤️❤️❤️❤️abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

	@Test
	public void testSize() {
		Alphabet alphabet = AlphabetFactory.of("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ");
		assertEquals("The size of the alphabet is wrong", 52, alphabet.size());
	}

	@Test
	public void testContainsCodePoint() {
		Alphabet alphabet = AlphabetFactory.of(randomAlphabet);
		int[] codePoints = randomAlphabet.codePoints()
				.toArray();
		for (int codePoint : codePoints) {
			assertTrue("Should contain code point: " + codePoint, alphabet.containsCodePoint(codePoint));
		}
	}

	@Test
	public void testIndexOfAndCodePointAt() {
		Alphabet alphabet = AlphabetFactory.of(randomAlphabet);
		for (int i = 0; i < alphabet.size(); i++) {
			assertTrue("Inconsistency at index #" + i, alphabet.indexOf(alphabet.codePointAt(i)) == i);
		}
	}

	@Test
	public void testContainsAllCollection() {
		Alphabet alphabet = AlphabetFactory.of(randomAlphabet);
		List<Integer> codePoints = randomAlphabet.codePoints()
				.boxed()
				.limit(alphabet.size())
				.collect(Collectors.toList());
		assertTrue("Does not detect the entire collection", alphabet.containsAll(codePoints));
	}

	@Test
	public void testContainsAllArray() {
		Alphabet alphabet = AlphabetFactory.of(randomAlphabet);
		Integer[] codePoints = randomAlphabet.codePoints()
				.boxed()
				.limit(alphabet.size())
				.toArray(Integer[]::new);
		assertTrue("Does not detect the entire array", alphabet.containsAll(codePoints));
	}
}

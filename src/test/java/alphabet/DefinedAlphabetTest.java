package alphabet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DefinedAlphabetTest {

	private static final String randomAlphabet = "😀😇🤠😪👾💀👨🤜🏻☂️😂😂😂😂😂😘😘😘😘😘❤️❤️❤️❤️❤️abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private Alphabet alphabet;

	@Before
	public void setup() {
		this.alphabet = AlphabetFactory.of(randomAlphabet);
	}

	@After
	public void tearDown() {
		this.alphabet = null;
	}

	@Test
	public void testConstructorString() {
		Alphabet alphabet = new DefinedAlphabet("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ");
		assertEquals("The size of the alphabet does not match the expected size", 52, alphabet.size());
	}

	@Test
	public void testConstructorCollection() {
		List<Integer> values = IntStream.range(0, 100)
				.boxed()
				.collect(Collectors.toList());
		Alphabet alphabet = new DefinedAlphabet(values);
		assertEquals("The size of the alphabet does not match the expected size", values.size(), alphabet.size());
	}

	@Test
	public void testSize() {
		Alphabet alphabet = AlphabetFactory.of("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ");
		assertEquals("The size of the alphabet is wrong", 52, alphabet.size());
	}

	@Test
	public void testContainsCodePoint() {
		int[] codePoints = randomAlphabet.codePoints()
				.toArray();
		for (int codePoint : codePoints) {
			assertTrue("Should contain code point: " + codePoint, alphabet.containsCodePoint(codePoint));
		}
	}

	@Test
	public void testIndexOfAndCodePointAt() {
		for (int i = 0; i < alphabet.size(); i++) {
			assertTrue("Inconsistency at index #" + i, alphabet.indexOf(alphabet.codePointAt(i)) == i);
		}
	}

	@Test
	public void testContainsAllCollection() {
		List<Integer> codePoints = randomAlphabet.codePoints()
				.boxed()
				.limit(alphabet.size())
				.collect(Collectors.toList());
		assertTrue("Does not detect the entire collection", alphabet.containsAll(codePoints));
	}

	@Test
	public void testContainsAllArray() {
		Integer[] codePoints = randomAlphabet.codePoints()
				.boxed()
				.limit(alphabet.size())
				.toArray(Integer[]::new);
		assertTrue("Does not detect the entire array", alphabet.containsAll(codePoints));
	}
}

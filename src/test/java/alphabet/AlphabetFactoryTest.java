package alphabet;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.Test;

public class AlphabetFactoryTest {

	@Test
	public void testOfString() {
		String text = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		Alphabet alphabet = AlphabetFactory.of(text);
		assertEquals("The returned Alphabet is not of the right size", 52, alphabet.size());
	}

	@Test
	public void testOfCollection() {
		List<Integer> values = IntStream.range(0, 100)
				.boxed()
				.collect(Collectors.toList());
		Alphabet alphabet = AlphabetFactory.of(values);
		assertEquals("The returned Alphabet is not of the right size", values.size(), alphabet.size());
	}
}

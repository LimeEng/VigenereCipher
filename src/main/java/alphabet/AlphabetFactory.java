package alphabet;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class AlphabetFactory {

	// To prevent instantiation
	private AlphabetFactory() {}

	public static Alphabet of(String characters) {
		return getAlphabet(characters);
	}

	public static Alphabet of(Collection<Integer> codePoints) {
		return getAlphabet(codePoints);
	}

	private static Alphabet getAlphabet(Stream<Integer> codePoints) {
		return new DefinedAlphabet(distinct(codePoints).collect(Collectors.toList()));
	}

	private static Alphabet getAlphabet(String text) {
		return getAlphabet(text.codePoints()
				.boxed());
	}

	private static Alphabet getAlphabet(Collection<Integer> codePoints) {
		return getAlphabet(codePoints.stream());
	}

	private static Stream<Integer> distinct(Stream<Integer> codePoints) {
		return codePoints.distinct();
	}
}

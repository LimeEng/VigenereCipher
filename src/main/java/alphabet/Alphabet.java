package alphabet;

import java.util.Arrays;
import java.util.Collection;

public interface Alphabet {

	int size();

	int codePointAt(int index) throws IndexOutOfBoundsException;

	int indexOf(int codePoint);

	boolean containsCodePoint(int codePoint);

	default boolean containsAll(Collection<Integer> codePoints) {
		return codePoints.stream()
				.allMatch(this::containsCodePoint);
	}

	default boolean containsAll(Integer... codePoints) {
		return containsAll(Arrays.asList(codePoints));
	}
}

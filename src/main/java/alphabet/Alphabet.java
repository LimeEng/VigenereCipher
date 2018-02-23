package alphabet;

import java.util.Arrays;
import java.util.Collection;

public interface Alphabet {

	public int size();

	public int codePointAt(int index) throws IndexOutOfBoundsException;

	public int indexOf(int codePoint);

	public boolean containsCodePoint(int codePoint);

	default boolean containsAll(Collection<Integer> codePoints) {
		return codePoints.stream()
				.allMatch(this::containsCodePoint);
	}

	default boolean containsAll(Integer... codePoints) {
		return containsAll(Arrays.asList(codePoints));
	}
}

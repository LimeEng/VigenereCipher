package alphabet;

import java.util.List;
import java.util.stream.Collectors;

public class DefinedAlphabet implements Alphabet {

	private final List<Integer> codePoints;

	public DefinedAlphabet(String characters) {
		this(characters.codePoints()
				.boxed()
				.collect(Collectors.toList()));
	}

	public DefinedAlphabet(List<Integer> codePoints) {
		this.codePoints = codePoints;
	}

	@Override
	public int size() {
		return codePoints.size();
	}

	@Override
	public int codePointAt(int index) throws IndexOutOfBoundsException {
		return codePoints.get(index);
	}

	@Override
	public int indexOf(int codePoint) {
		return codePoints.indexOf(codePoint);
	}

	@Override
	public boolean containsCodePoint(int codePoint) {
		return codePoints.contains(codePoint);
	}
}

package alphabet;

public class DefinedAlphabet implements Alphabet {

	private final String alphabet;

	public DefinedAlphabet(String characters) {
		this.alphabet = characters;
	}

	@Override
	public int size() {
		return alphabet.codePointCount(0, alphabet.length());
	}

	@Override
	public int codePointAt(int index) throws IndexOutOfBoundsException {
		return alphabet.codePointAt(index);
	}

	@Override
	public int indexOf(int codePoint) {
		return alphabet.indexOf(codePoint);
	}

	@Override
	public boolean containsCodePoint(int codePoint) {
		return alphabet.contains(new String(Character.toChars(codePoint)));
	}
}

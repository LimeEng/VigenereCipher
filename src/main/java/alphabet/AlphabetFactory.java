package alphabet;

public class AlphabetFactory {

	public final static String extendedAlphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890 !#%&/()=?<>-_^*{}[]\n\"\\;:.,";
	public final static String alphanumericAlphabet = "abcdefghijklmnopqrstuvwxyzåäöABCDEFGHIJKLMNOPQRSTUVWXYZÅÄÖ1234567890";

	public static Alphabet of(String characters) {
		return getAlphabet(characters);
	}

	public static Alphabet extended() {
		return getAlphabet(extendedAlphabet);
	}

	public static Alphabet alphanumeric() {
		return getAlphabet(alphanumericAlphabet);
	}
	
	private static Alphabet getAlphabet(String text) {
		return new DefinedAlphabet(distinct(text));
	}

	private static String distinct(String text) {
		StringBuilder sb = text.codePoints()
				.distinct()
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append);
		return sb.toString();
	}
}

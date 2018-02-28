package core;

import java.util.stream.IntStream;

import alphabet.Alphabet;
import alphabet.AlphabetFactory;
import repl.CommandLine;
import util.PredefinedAlphabetStrings;

public class Main {

	public static void main(String[] args) {
		// String text = "This is my very secret text!";
		String text = "😂😂😂😂😂😘😘😘😘😘❤️❤️❤️❤️❤️";
		// String text = "😀😇🤠😪👾💀👨🤜🏻☂️";
		String key = "Password123";

		Alphabet alphabet = AlphabetFactory.of(merge(text, PredefinedAlphabetStrings.extendedAlphabet));

		VigenereCipher cipher = new VigenereCipher(alphabet);

		String encrypted = cipher.encrypt(text, key);
		String decrypted = cipher.decrypt(encrypted, key);

		System.out.println("Plaintext: [" + text + "]");
		System.out.println("Key: [" + key + "]");
		System.out.println("Encrypted: [" + encrypted + "]");
		System.out.println("Decrypted: [" + decrypted + "]");

		System.out.println((text.equals(decrypted)) ? "The decryption was successful!"
				: "The decrypted text does not match the plain text");

		CommandLine line = new CommandLine();
		line.run();
	}

	private static String merge(String a, String b) {
		StringBuilder sb = IntStream.concat(a.codePoints(), b.codePoints())
				.distinct()
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append);
		return sb.toString();
	}
}

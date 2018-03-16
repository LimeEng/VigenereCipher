package ciphers;

import alphabet.Alphabet;

public class VigenereCipher {

	private final Alphabet alphabet;

	public VigenereCipher(Alphabet alphabet) {
		this.alphabet = alphabet;
	}

	public String encrypt(String text, String key) {
		return cipher(text, key, CipherOperation.ENCRYPT);
	}

	public String decrypt(String text, String key) {
		return cipher(text, key, CipherOperation.DECRYPT);
	}

	public int cipher(int text, int key, CipherOperation operation) {
		if (alphabet.containsAll(text, key)) {
			int indexOfChar = alphabet.indexOf(text);
			int indexOfKey = alphabet.indexOf(key);
			int modifiedIndex = getModifiedIndex(indexOfChar, indexOfKey, operation);
			return alphabet.codePointAt(modifiedIndex);
		}
		return text;
	}

	private String cipher(String text, String key, CipherOperation operation) {
		StringBuilder sb = new StringBuilder();

		int textLength = text.length();
		int keyLength = key.length();
		int keyOffset = 0;

		for (int textOffset = 0; textOffset < textLength;) {
			int chr = text.codePointAt(textOffset);
			int keyChar = key.codePointAt(keyOffset % keyLength);

			sb.appendCodePoint(cipher(chr, keyChar, operation));

			textOffset += Character.charCount(chr);
			keyOffset += Character.charCount(keyChar);
		}
		return sb.toString();
	}

	private int getModifiedIndex(int indexOfChar, int indexOfKey, CipherOperation operation) {
		int modifiedIndex = 0;
		if (operation == CipherOperation.ENCRYPT) {
			modifiedIndex = (indexOfChar + indexOfKey);
		} else {
			modifiedIndex = (indexOfChar - indexOfKey);
		}
		modifiedIndex %= alphabet.size();
		if (modifiedIndex < 0) {
			modifiedIndex = alphabet.size() - Math.abs(modifiedIndex);
		}
		return modifiedIndex;
	}

	public enum CipherOperation {
		ENCRYPT,
		DECRYPT;
	}
}

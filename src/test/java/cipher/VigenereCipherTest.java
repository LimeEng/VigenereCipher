package cipher;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import alphabet.AlphabetFactory;
import core.VigenereCipher;

public class VigenereCipherTest {

	private VigenereCipher cipher;
	private String basicAlphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

	@Before
	public void setup() {
		cipher = new VigenereCipher(AlphabetFactory.of(basicAlphabet));
	}

	@After
	public void cleanup() {
		cipher = null;
	}

	@Test
	public void testBasicEncryption() {
		String text = "This is a rather long sentence which should be properly encrypted and later decrypted";
		String key = "password";
		String expected = "ihAK wJ p JsPvvu lGFC JhCtwFys zwiuz GyrJlv xs sGoHwNzP tnuJUDKhs sFz CdIeJ zstuNpLwz";
		assertEquals("The encryption does not match the expected result", expected, cipher.encrypt(text, key));
	}

	@Test
	public void testBasicDecryption() {
		String text = "ihAK wJ p JsPvvu lGFC JhCtwFys zwiuz GyrJlv xs sGoHwNzP tnuJUDKhs sFz CdIeJ zstuNpLwz";
		String key = "password";
		String expected = "This is a rather long sentence which should be properly encrypted and later decrypted";
		assertEquals("The decryption does not match the expected result", expected, cipher.decrypt(text, key));
	}
	
	@Test
	public void testThatEncryptionDoesSomething() {
		String text  = "This is a random sentence";
		String key = "Random password";
		assertNotEquals("The encryption does nothing", text, cipher.encrypt(text, key));
	}
	
	@Test
	public void testThatDecryptionDoesSomething() {
		String text = "This is a random sentence";
		String key = "Random password";
		assertNotEquals("The encryption does nothing", text, cipher.decrypt(text, key));
	}

	@Test
	public void testEncryptionAndDecryption() {
		for (int i = 0; i < 1000; i++) {
			String text = longRandomString();
			String key = longRandomString();
			String encrypted = cipher.encrypt(text, key);
			String decrypted = cipher.decrypt(encrypted, key);
			assertTrue("Text were not preserved during encryption and back", text.equals(decrypted));
		}
	}

	private String uuidString() {
		String uuid = UUID.randomUUID()
				.toString();
		return uuid;
	}

	private String emojiString() {
		return "😀😇🤠😪👾💀👨🤜🏻☂️😂😂😂😂😂😘😘😘😘😘❤️❤️❤️❤️❤️";
	}

	private String randomString() {
		if (ThreadLocalRandom.current()
				.nextBoolean()) {
			return emojiString();
		}
		return uuidString();
	}

	private String longRandomString() {
		return Stream.generate(this::randomString)
				.limit(10)
				.collect(Collectors.joining());
	}
}

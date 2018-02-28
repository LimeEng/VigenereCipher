package test_util.test;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

import test_util.CodePointSupplier;

public class CodePointSupplierTest {

	@Test
	public void basicCyclicTest() {
		String text = "dfaskjdhakjsdghasdgjuwdg😀😇🤠😪👾💀👨🤜🏻☂️wjadhjkasgdjasdghasjdhjksaldgasjhdsa";
		List<Integer> codePoints = text.codePoints().boxed().collect(Collectors.toList());
		CodePointSupplier supplier = new CodePointSupplier(text);

		for (int i = 0; i < 2000; i++) {
			Integer expected = codePoints.get(i % codePoints.size());
			Integer actual = supplier.get();
			assertEquals("The supplier does not cycle properly", expected, actual);
		}
	}
}
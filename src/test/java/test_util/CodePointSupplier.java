package test_util;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class CodePointSupplier implements Supplier<Integer> {

	private int counter = 0;
	private List<Integer> contents;

	public CodePointSupplier(String characters) {
		this.contents = characters.codePoints()
				.boxed()
				.collect(Collectors.toList());
	}

	@Override
	public Integer get() {
		Integer result = contents.get(counter % contents.size());
		counter++;
		return result;
	}
}
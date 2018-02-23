package repl.commands;

import java.util.Arrays;
import java.util.stream.Stream;

public enum Command {

	UNDEFINED, 
	EXIT,
	ENCRYPT,
	DECRYPT;

	public static Stream<Command> stream() {
		return Arrays.stream(Command.values());
	}
}

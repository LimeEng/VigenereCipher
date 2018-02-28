package repl;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

import alphabet.AlphabetFactory;
import core.VigenereCipher;
import repl.commands.Command;
import repl.commands.status.StatusCode;
import repl.commands.status.StatusMessage;

public class CommandLine {

	private VigenereCipher cipher = new VigenereCipher(AlphabetFactory.extended());

	public void run() {
		try (Scanner scan = new Scanner(System.in)) {
			Supplier<String> input = () -> {
				System.out.print("> ");
				return scan.nextLine();
			};

			Operation<String, Entry<Command, String>> interpretOperation = text -> {

				List<String> parts = new ArrayList<>(Arrays.asList(text.split(" ")));
				String command = parts.remove(0);

				Optional<Command> opt = Command.stream()
						.filter(e -> e.name()
								.toLowerCase()
								.equals(command.toLowerCase()
										.trim()))
						.findAny();

				String rest = String.join(" ", parts);
				if (opt.isPresent()) {
					return new SimpleEntry<>(opt.get(), rest);
				}
				return new SimpleEntry<>(Command.UNDEFINED, rest);
			};

			Operation<Entry<Command, String>, StatusMessage> commandOperation = (entry) -> {

				Command command = entry.getKey();
				String arguments = entry.getValue();

				switch (command) {
				case ENCRYPT:
				case DECRYPT:
					String[] args = arguments.split(" ");
					if (args.length != 2) {
						return new StatusMessage(StatusCode.UNDEFINED,
								"Invalid syntax: [encrypt|decrypt] [key] [text]");
					}
					String key = args[0];
					String text = args[1];
					if (command == Command.ENCRYPT) {
						return new StatusMessage(StatusCode.OK, cipher.encrypt(text, key));
					}
					return new StatusMessage(StatusCode.OK, cipher.decrypt(text, key));
				case EXIT:
					return new StatusMessage(StatusCode.EXIT, "Have a nice day!");
				case UNDEFINED:
				default:
					return new StatusMessage(StatusCode.UNDEFINED, "Invalid syntax: [encrypt|decrypt] [key] [text]");
				}
			};

			Function<String, StatusMessage> mainFunction = value -> interpretOperation.pipe(commandOperation)
					.execute(value);

			Function<StatusMessage, StatusCode> resultHandler = message -> {
				System.out.println(message.getMessage());
				return message.getCode();
			};

			Predicate<StatusCode> quitCondition = code -> code == StatusCode.EXIT;

			Stream.generate(input)
					.map(mainFunction)
					.map(resultHandler)
					.noneMatch(quitCondition);
		}
	}
}

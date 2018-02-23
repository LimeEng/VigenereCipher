package repl.commands.status;

public class StatusMessage {

	private final StatusCode code;
	private final String message;

	public StatusMessage(StatusCode code, String message) {
		this.code = code;
		this.message = message;
	}

	public StatusCode getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
}

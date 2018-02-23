package repl;

public interface Operation<I, O> {

	O execute(I value);

	default <R> Operation<I, R> pipe(Operation<O, R> source) {
		return value -> source.execute(execute(value));
	}

	static <I, O> Operation<I, O> of(Operation<I, O> source) {
		return source;
	}
}

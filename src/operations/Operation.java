package operations;

public interface Operation<T> {

    Boolean evaluate(T left, T right);
}

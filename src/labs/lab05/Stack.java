package labs.lab05;

public interface Stack<T> {
    void push(T t);
    T pop();
    T top();
    boolean isEmpty();
}

package hw2;

public interface MyList<E> {
    void add(E element);
    void add(int index, E element);
    E get(int i);
    int size();
    boolean isEmpty();
    void remove(int index);
}

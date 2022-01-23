package hw2;

import java.util.Iterator;

public class MyArrayList<E> implements MyList<E>, Iterable<E> {

    private int capacity;
    private int length;
    private Object[] array;
    private final int DEFAULT_CAPACITY = 10;
    private Iterator<E> Iterator;


    public MyArrayList() {
        capacity = DEFAULT_CAPACITY;
        array = new Object[capacity];
        length = 0;
    }

    public MyArrayList(int initCapacity) {
        capacity = initCapacity;
        array = new Object[capacity];
        length = 0;
    }

    @Override
    public void remove(int index) {
        checkIndexBound(index);
        System.arraycopy(array, index + 1, array, index, length - index);
        length--;
    }

    @Override
    public void add(E element) {
        if (length == capacity) {
            grow();
        }
        array[length] = element;
        length++;
    }

    private void grow() {
        int newCapacity = (int) (capacity * 1.5);
        Object[] newArray = new Object[newCapacity];
        System.arraycopy(array, 0, newArray, 0, capacity);
        capacity = newCapacity;
        array = newArray;
    }

    private void checkIndexBound(int index) {
        if (index < 0 || index >= length) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public void add(int index, E element) {
        checkIndexBound(index);
        if (length == capacity) {
            grow();
        }
        System.arraycopy(array, index, array, index + 1, length - index);
        array[index] = element;
        length++;
    }

    @Override
    public E get(int i) {
        checkIndexBound(i);
        return (E) this.array[i];
    }

    @Override
    public int size() {
        return length;
    }

    @Override
    public boolean isEmpty() {
        return length == 0;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            stringBuilder.append(array[i]);
            if (i != length - 1) {
                stringBuilder.append(", ");
            }
        }
        return "MyArrayList = " + stringBuilder.toString();
    }

    @Override
    public Iterator<E> iterator() {

        return new Iterator<E>() {
            int index = 0;

            @Override
            public boolean hasNext() {
                return index < length;
            }

            @Override
            public E next() {
                E el = (E) array[index];
                index++;
                return el;
            }
        };
    }
}

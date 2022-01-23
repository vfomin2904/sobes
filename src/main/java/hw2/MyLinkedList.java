package hw2;

public class MyLinkedList<E> implements MyList<E>{

    private Node<E> first;
    private Node<E> last;
    private int length;

    public MyLinkedList() {
        length = 0;
    }

    private static class Node <E> {
        private E value;
        private Node<E> next;
        private Node<E> prev;

        public Node(E value, Node next, Node prev) {
            this.value = value;
            this.next = next;
            this.prev = prev;
        }
    }

    @Override
    public void add(E element) {
        if (first == null) {
            first = new Node<>(element, null, null);
            last = first;
        } else {
            Node<E> newNode = new Node<>(element, null, last);
            last.next = newNode;
            last = newNode;
        }
        length++;
    }

    @Override
    public void add(int index, E element) {
        checkIndexBound(index);

        Node<E> left = first;
        for (int i = 0; i < index; i++) {
            left = left.next;
        }
        Node<E> right = left.next;
        Node<E> newNode = new Node<>(element, right, left);
        left.next = newNode;
        right.prev = newNode;
        length++;
    }

    private void checkIndexBound(int index) {
        if (index < 0 || index >= length) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public E get(int i) {
        Node<E> node = first;
        for (int j = 0; j < i; j++) {
            node = node.next;
        }
        return node.value;
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
    public void remove(int index) {
        checkIndexBound(index);

        Node<E> node = first;
        for (int j = 0; j < index; j++) {
            node = node.next;
        }
        Node<E> left = node.prev;
        Node<E> right = node.next;

        if (left != null) {
            left.next = right;
        } else {
            first = right;
        }
        if (right != null) {
            right.prev = left;
        } else {
            last = left;
        }
        length--;
    }

    @Override
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder();
        Node<E> node = first;
        for (int i = 0; i < length; i++) {
            stringBuilder.append(node.value);
            if (i != length - 1) {
                stringBuilder.append(", ");
            }
            node = node.next;
        }

        return "MyLinkedList = " + stringBuilder;
    }
}

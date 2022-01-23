package hw2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(1, 4);
        list.add(2, 5);

        System.out.println(list);

        list.remove(4);
        System.out.println(list);

        list.remove(2);

        System.out.println(list);
    }
}

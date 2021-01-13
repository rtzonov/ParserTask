package by.epam.tc.parser.util;

import java.util.EmptyStackException;

public class Stack<E> extends java.util.Stack<E> {
    public Stack() {
    }

    public E push(E item) {
        addElement(item);

        return item;
    }

    public E pop() {
        E       obj;
        int     len = size();

        obj = peek();
        removeElementAt(len - 1);

        return obj;
    }

    public E peek() {
        int     len = size();

        if (len == 0)
            throw new EmptyStackException();
        return elementAt(len - 1);
    }

    public boolean empty() {
        return size() == 0;
    }

    public int search(Object o) {
        int i = lastIndexOf(o);

        if (i >= 0) {
            return size() - i;
        }
        return -1;
    }
}

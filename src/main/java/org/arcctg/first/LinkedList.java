package org.arcctg.first;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<T> implements Iterable<T> {
    private Node<T> first = null;
    private Node<T> last = null;
    private int size = 0;

    public LinkedList() {}

    public LinkedList(Collection<? extends T> c) {
        this();
        c.forEach(this::addLast);
    }

    public T getFirst() {
        if (first == null) {
            throw new NoSuchElementException();
        }

        return first.value;
    }
    public T getLast() {
        if (last == null) {
            throw new NoSuchElementException();
        }

        return last.value;
    }

    public T get(int index) {
        return getAt(index).value;
    }

    public void addFirst(T value) {
        Node<T> second = first;
        Node<T> newNode = new Node<>(null, second, value, 0);
        first = newNode;

        if (second == null) {
            last = newNode;
        } else {
            second.left = newNode;
            second.index = 1;

            Node<T> next = second.right;

            while (next != null) {
                next.index++;
                next = next.right;
            }
        }

        size++;
    }

    public void addLast(T value) {
        Node<T> l = last;
        Node<T> newNode = new Node<>(l, null, value, 0);
        last = newNode;

        if (l == null) {
            first = newNode;
        } else {
            l.right = newNode;
            last.index = l.index + 1;
        }

        size++;
    }

    @SafeVarargs
    public final void insert(int index, T... values) {
        Node<T> current, left;

        if (index == size) {
            current = null;
            left = last;
        } else {
            current = getAt(index);
            left = current.left;
        }

        int size = values.length;

        Node<T> next = current;
        while (next != null) {
            next.index += size;
            next = next.right;
        }

        for (int i = 0; i < values.length; i++) {
            Node<T> newNode = new Node<>(left, current, values[i], index + i);

            try {
                left.right = newNode;
            } catch (NullPointerException e) {
                first = newNode;
            }

            left = newNode;
        }

        if (current != null)
            current.left = left;
        else
            last = left;

        this.size += size;
    }

    public void remove(T value) {
        Node<T> current = first;

        for (int i = 0; i < size; i++) {
            if (current.value == value) {
                removeAt(i);
                return;
            }
            current = current.right;
        }

        throw new NoSuchElementException();
    }

    public void removeAt(int index) {
        checkIndex(index);

        Node<T> current = getAt(index);

        if (current.left != null) {
            current.left.right = current.right;
        } else {
            first = current.right;
        }

        if (current.right != null) {
            current.right.left = current.left;
        } else {
            last = current.left;
        }

        Node<T> right = current.right;

        while (right != null) {
            right.index--;
            right = right.right;
        }

        size--;
    }

    public void removeFirst() {
        removeAt(0);

        Node<T> next = first;

        for (int i = 0; i < size; i++) {
            next.index = i;
            next = next.right;
        }
    }

    public void removeLast() {
        removeAt(size - 1);
    }

    public void change(int index, T value) {
        Node<T> current = getAt(index);

        current.value = value;
    }

    public int size() {
        return size;
    }

    public void clear() {
        first = null;
        last = null;
        size = 0;
    }

    public LinkedList<T> clone() {
        LinkedList<T> clonedList = new LinkedList<>();
        Node<T> current = this.first;

        for (int i = 0; i < size; i++) {
            clonedList.addLast(current.value);
            current = current.right;
        }

        return clonedList;
    }

    @SuppressWarnings("unchecked")
    public T[] toArray() {
        T[] array = (T[]) new Object[size];
        Node<T> current = first;

        for (int i = 0; i < size; i++) {
            array[i] = current.value;
            current = current.right;
        }

        return array;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    private Node<T> getAt(int index) {
        checkIndex(index);

        Node<T> current = first;

        while (current.index != index) {
            current = current.right;
        }

        return current;
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<T> {
        private Node<T> current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T value = current.value;
            current = current.right;
            return value;
        }
    }
}

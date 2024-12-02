package org.arcctg.second;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<T> implements Iterable<T>  {
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
        Node<T> f = first;
        Node<T> newNode = new Node<>(null, f, value);
        first = newNode;

        if (f == null) {
            last = newNode;
        } else {
            f.left = newNode;
        }

        size++;
    }

    public void addLast(T value) {
        Node<T> l = last;
        Node<T> newNode = new Node<>(l, null, value);
        last = newNode;

        if (l == null) {
            first = newNode;
        } else {
            l.right = newNode;
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

        for (T value : values) {
            Node<T> newNode = new Node<>(left, current, value);

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

        size += values.length;
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

        size--;
    }

    public void removeFirst() {
        removeAt(0);
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

        for (int i = 0; i < index; i++) {
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

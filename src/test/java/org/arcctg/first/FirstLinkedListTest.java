package org.arcctg.first;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class FirstLinkedListTest {

    @Test
    void getFirst() {
        LinkedList<Integer> list = new LinkedList<>();
        list.insert(0, 5, 2, 3, 7, 12);

        assertEquals(5, list.getFirst());

        list.removeFirst();
        assertEquals(2, list.getFirst());

        list.clear();
        assertThrows(NoSuchElementException.class, list::getFirst);
    }

    @Test
    void getLast() {
        LinkedList<Integer> list = new LinkedList<>();
        list.insert(0, 5, 2, 3, 7, 12);

        assertEquals(12, list.getLast());

        list.removeLast();
        assertEquals(7, list.getLast());

        list.clear();
        assertThrows(NoSuchElementException.class, list::getLast);
    }

    @Test
    void get() {
        LinkedList<Integer> list = new LinkedList<>();
        list.insert(0, 5, 2, 3, 7, 12);

        assertEquals(5, list.get(0));

        list.removeAt(0);
        assertEquals(2, list.get(0));

        list.removeAt(2);
        assertEquals(12, list.get(2));

        assertThrows(IndexOutOfBoundsException.class, () -> list.get(78));

        list.clear();
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(0));
    }

    @Test
    void addFirst() {
        LinkedList<Integer> list = new LinkedList<>();
        list.insert(0, 5, 2, 3, 7, 12);

        list.addFirst(3);
        assertEquals(3, list.getFirst());

        list.addFirst(2);
        assertEquals(2, list.getFirst());
    }

    @Test
    void addLast() {
        LinkedList<Integer> list = new LinkedList<>();
        list.insert(0, 5, 2, 3, 7, 12);

        list.addLast(76);
        assertEquals(76, list.getLast());

        list.addLast(43);
        assertEquals(43, list.getLast());
    }

    @Test
    void insert() {
        LinkedList<Integer> list = new LinkedList<>();

        list.insert(0, 5, 2, 3, 7, 12);
        assertEquals("[5, 2, 3, 7, 12]", Arrays.toString(list.toArray()));

        list.insert(3, 8, 99, 0, 23, 86);
        assertEquals(
                "[5, 2, 3, 8, 99, 0, 23, 86, 7, 12]",
                Arrays.toString(list.toArray())
        );

        list.insert(list.size(), 23, 6);
        assertEquals(
                "[5, 2, 3, 8, 99, 0, 23, 86, 7, 12, 23, 6]",
                Arrays.toString(list.toArray())
        );
    }

    @Test
    void remove() {
        LinkedList<Integer> list = new LinkedList<>();
        list.insert(0, 5, 2, 3, 7, 12);

        list.remove(2);
        assertEquals(3, list.get(1));

        list.remove(7);
        assertEquals(12, list.get(2));

        assertThrows(NoSuchElementException.class, () -> list.remove(6342));
        assertThrows(NoSuchElementException.class, () -> list.remove(-235));
    }

    @Test
    void removeAt() {
        LinkedList<Integer> list = new LinkedList<>();
        list.insert(0, 9, 674, 3, 32, 153, 598, 89);

        list.removeAt(2);
        assertEquals(32, list.get(2));

        list.removeAt(4);
        assertEquals(89, list.get(4));

        assertThrows(IndexOutOfBoundsException.class, () -> list.removeAt(list.size()));
        assertThrows(IndexOutOfBoundsException.class, () -> list.removeAt(-1));
    }

    @Test
    void removeFirst() {
        LinkedList<Integer> list = new LinkedList<>();
        list.insert(0, 9, 674, 3, 32, 153, 598, 89);

        list.removeFirst();
        assertEquals(674, list.getFirst());

        list.removeFirst();
        assertEquals(3, list.getFirst());

        list.clear();
        assertThrows(IndexOutOfBoundsException.class, list::removeFirst);
    }

    @Test
    void removeLast() {
        LinkedList<Integer> list = new LinkedList<>();
        list.insert(0, 9, 674, 3, 32, 153, 598, 89);

        list.removeLast();
        assertEquals(598, list.getLast());

        list.removeLast();
        assertEquals(153, list.getLast());

        list.clear();
        assertThrows(IndexOutOfBoundsException.class, list::removeLast);
    }

    @Test
    void change() {
        LinkedList<Integer> list = new LinkedList<>();
        list.insert(0, 9, 674, 3, 32, 153, 598, 89);

        list.change(2, 623);
        assertEquals(623, list.get(2));

        list.change(5, 11);
        assertEquals(11, list.get(5));

        list.clear();
        assertThrows(IndexOutOfBoundsException.class, () -> list.change(-1, 22));
    }

    @Test
    void size() {
        LinkedList<Integer> list = new LinkedList<>();
        list.insert(0, 9, 674, 3, 32, 153, 598, 89);

        assertEquals(7, list.size());

        list.removeLast();
        list.removeLast();
        assertEquals(5, list.size());

        list.clear();
        assertEquals(0, list.size());
    }

    @Test
    void clear() {
        LinkedList<Integer> list = new LinkedList<>();
        list.insert(0, 9, 674, 3, 32, 153, 598, 89);
        list.clear();

        assertEquals(0, list.size());
        assertThrows(NoSuchElementException.class, list::getFirst);
    }

    @Test
    void testClone() {
        LinkedList<Integer> list = new LinkedList<>();
        list.insert(0, 9, 674, 3, 32, 153, 598, 89);
        LinkedList<Integer> cloned = list.clone();

        assertEquals(list.getFirst(), cloned.getFirst());
        assertEquals(list.getLast(), cloned.getLast());

        cloned.removeFirst();
        assertNotEquals(list.getFirst(), cloned.getFirst());
        assertNotEquals(list.size(), cloned.size());
    }

    @Test
    void toArray() {
        LinkedList<Integer> list = new LinkedList<>();

        list.insert(0, 5, 78, 3, 8, 12);
        assertEquals("[5, 78, 3, 8, 12]", Arrays.toString(list.toArray()));

        list.insert(3, 8, 99, 123, 23, 86);
        assertEquals(
                "[5, 78, 3, 8, 99, 123, 23, 86, 8, 12]",
                Arrays.toString(list.toArray())
        );

        list.insert(list.size(), 23, 6);
        assertEquals(
                "[5, 78, 3, 8, 99, 123, 23, 86, 8, 12, 23, 6]",
                Arrays.toString(list.toArray())
        );
    }
}
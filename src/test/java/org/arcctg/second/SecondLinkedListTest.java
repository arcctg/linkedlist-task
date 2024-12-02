package org.arcctg.second;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class SecondLinkedListTest {

    @Test
    void getFirst() {
        // Arrange
        LinkedList<Integer> list = new LinkedList<>(List.of(5, 2, 3, 7, 12));

        // Act
        Integer first = list.getFirst();

        // Assert
        assertEquals(5, first);
    }

    @Test
    void getFirstThrowsExceptionWhenEmpty() {
        // Arrange
        LinkedList<Integer> list = new LinkedList<>();

        // Act & Assert
        assertThrows(NoSuchElementException.class, list::getFirst);
    }

    @Test
    void getLast() {
        // Arrange
        LinkedList<Integer> list = new LinkedList<>(List.of(5, 2, 3, 7, 12));

        // Act
        Integer last = list.getLast();

        // Assert
        assertEquals(12, last);
    }

    @Test
    void getLastThrowsExceptionWhenEmpty() {
        // Arrange
        LinkedList<Integer> list = new LinkedList<>();

        // Act & Assert
        assertThrows(NoSuchElementException.class, list::getLast);
    }

    @Test
    void get() {
        // Arrange
        LinkedList<Integer> list = new LinkedList<>(List.of(5, 2, 3, 7, 12));

        // Act
        Integer elementAtIndex = list.get(2);

        // Assert
        assertEquals(3, elementAtIndex);
    }

    @Test
    void getThrowsExceptionForInvalidIndex() {
        // Arrange
        LinkedList<Integer> list = new LinkedList<>(List.of(5, 2, 3, 7, 12));

        // Act & Assert
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(10));
    }

    @Test
    void addFirst() {
        // Arrange
        LinkedList<Integer> list = new LinkedList<>(List.of(2, 3, 7, 12));

        // Act
        list.addFirst(5);

        // Assert
        assertEquals(5, list.getFirst());
    }

    @Test
    void addLast() {
        // Arrange
        LinkedList<Integer> list = new LinkedList<>(List.of(5, 2, 3, 7));

        // Act
        list.addLast(12);

        // Assert
        assertEquals(12, list.getLast());
    }

    @Test
    void insert() {
        // Arrange
        LinkedList<Integer> list = new LinkedList<>(List.of(5, 2, 3, 7, 12));

        // Act
        list.insert(3, 99);

        // Assert
        assertEquals(99, list.get(3));
        assertEquals(7, list.get(4));
    }

    @Test
    void insertThrowsExceptionForInvalidIndex() {
        // Arrange
        LinkedList<Integer> list = new LinkedList<>(List.of(5, 2, 3, 7, 12));

        // Act & Assert
        assertThrows(IndexOutOfBoundsException.class, () -> list.insert(-1, 99));
        assertThrows(IndexOutOfBoundsException.class, () -> list.insert(10, 99));
    }

    @Test
    void remove() {
        // Arrange
        LinkedList<Integer> list = new LinkedList<>(List.of(5, 2, 3, 7, 12));

        // Act
        list.remove(3);

        // Assert
        assertEquals(4, list.size());
        assertEquals(12, list.get(3));
    }

    @Test
    void removeThrowsExceptionForMissingElement() {
        // Arrange
        LinkedList<Integer> list = new LinkedList<>(List.of(5, 2, 3, 7, 12));

        // Act & Assert
        assertThrows(NoSuchElementException.class, () -> list.remove(99));
    }

    @Test
    void removeAt() {
        // Arrange
        LinkedList<Integer> list = new LinkedList<>(List.of(5, 2, 3, 7, 12));

        // Act
        list.removeAt(2);

        // Assert
        assertEquals(4, list.size());
        assertEquals(7, list.get(2));
    }

    @Test
    void removeAtThrowsExceptionForInvalidIndex() {
        // Arrange
        LinkedList<Integer> list = new LinkedList<>(List.of(5, 2, 3, 7, 12));

        // Act & Assert
        assertThrows(IndexOutOfBoundsException.class, () -> list.removeAt(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.removeAt(10));
    }

    @Test
    void removeFirst() {
        // Arrange
        LinkedList<Integer> list = new LinkedList<>(List.of(9, 674, 3, 32, 153, 598, 89));

        // Act
        list.removeFirst(); // Remove the first element (9)

        // Assert
        assertEquals(674, list.getFirst());

        // Act
        list.removeFirst(); // Remove the next first element (674)

        // Assert
        assertEquals(3, list.getFirst());
    }

    @Test
    void removeFirstThrowsExceptionWhenEmpty() {
        // Arrange
        LinkedList<Integer> list = new LinkedList<>();

        // Act & Assert
        assertThrows(IndexOutOfBoundsException.class, list::removeFirst);
    }

    @Test
    void removeLast() {
        // Arrange
        LinkedList<Integer> list = new LinkedList<>(List.of(9, 674, 3, 32, 153, 598, 89));

        // Act
        list.removeLast(); // Remove the last element (89)

        // Assert
        assertEquals(598, list.getLast());

        // Act
        list.removeLast(); // Remove the next last element (598)

        // Assert
        assertEquals(153, list.getLast());
    }

    @Test
    void removeLastThrowsExceptionWhenEmpty() {
        // Arrange
        LinkedList<Integer> list = new LinkedList<>();

        // Act & Assert
        assertThrows(IndexOutOfBoundsException.class, list::removeLast);
    }

    @Test
    void change() {
        // Arrange
        LinkedList<Integer> list = new LinkedList<>(List.of(5, 2, 3, 7, 12));

        // Act
        list.change(3, 99);

        // Assert
        assertEquals(99, list.get(3));
    }

    @Test
    void changeThrowsExceptionForInvalidIndex() {
        // Arrange
        LinkedList<Integer> list = new LinkedList<>(List.of(5, 2, 3, 7, 12));

        // Act & Assert
        assertThrows(IndexOutOfBoundsException.class, () -> list.change(-1, 99));
        assertThrows(IndexOutOfBoundsException.class, () -> list.change(10, 99));
    }

    @Test
    void size() {
        // Arrange
        LinkedList<Integer> list = new LinkedList<>(List.of(5, 2, 3, 7, 12));

        // Act
        int size = list.size();

        // Assert
        assertEquals(5, size);
    }

    @Test
    void clear() {
        // Arrange
        LinkedList<Integer> list = new LinkedList<>(List.of(5, 2, 3, 7, 12));

        // Act
        list.clear();

        // Assert
        assertEquals(0, list.size());
        assertThrows(NoSuchElementException.class, list::getFirst);
    }

    @Test
    void testClone() {
        // Arrange
        LinkedList<Integer> list = new LinkedList<>(List.of(5, 2, 3, 7, 12));

        // Act
        LinkedList<Integer> cloned = list.clone();

        // Assert
        assertEquals(list.size(), cloned.size());
        assertEquals(list.getFirst(), cloned.getFirst());
        assertEquals(list.getLast(), cloned.getLast());

        cloned.removeAt(0);
        assertNotEquals(list.getFirst(), cloned.getFirst());
    }

    @Test
    void toArray() {
        // Arrange
        LinkedList<Integer> list = new LinkedList<>(List.of(5, 2, 3, 7, 12));

        // Act
        Object[] array = list.toArray();

        // Assert
        assertArrayEquals(new Integer[]{5, 2, 3, 7, 12}, array);
    }
}
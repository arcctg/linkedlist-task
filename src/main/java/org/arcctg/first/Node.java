package org.arcctg.first;

public class Node<T> {
    public Node<T> left;
    public Node<T> right;
    public T value;
    public int index;

    public Node(Node<T> left, Node<T> right, T value, int index) {
        this.left = left;
        this.right = right;
        this.value = value;
        this.index = index;
    }
}

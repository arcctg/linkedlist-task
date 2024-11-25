package org.arcctg.second;

public class Node<T> {
    public Node<T> left;
    public Node<T> right;
    public T value;

    public Node(Node<T> left, Node<T> right, T value) {
        this.left = left;
        this.right = right;
        this.value = value;
    }
}
package ru.job4j.list;

/**
 * Class OwnStack | Task Solution: Create Stack and Queue containers by using DynamicLinkedLlist container. [#160]
 * @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
 * @since 05.08.2018
 */
public class OwnStack<E> {

    private int size = 0;
    private Node<E> firstNode;
    private Node<E> lastNode;

    /**
     * Add element to stack.
     * @param value element.
     */
    public void push(E value) {
        Node<E> newElement;
        if (firstNode == null) {
            newElement = new Node<>(value);
            firstNode = newElement;
            lastNode = newElement;
        } else {
            newElement = new Node<>(lastNode, value);
            lastNode.next = newElement;
            lastNode = newElement;
        }
        this.size++;
    }

    /**
     * Poll first element from stack.
     * @return polled element.
     */
    public E poll() {
        if (this.size > 0) {
            E pollingElement = this.lastNode.element;
            this.lastNode = this.lastNode.prev;
            this.size--;
            return pollingElement;
        }
        return null;
    }

    /**
     * Class Node | Task Solution: Create Stack and Queue containers by using DynamicLinkedLlist container. [#160]
     * @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
     * @since 05.08.2018
     */
    private static class Node<E> {
        Node<E> next;
        Node<E> prev;
        private final E element;

        /**
         * Constructor.
         */
        Node(Node<E> prev, E element) {
            this.prev = prev;
            this.element = element;
        }

        /**
         * Constructor.
         */
        Node(E element) {
            this.element = element;
        }
    }
}
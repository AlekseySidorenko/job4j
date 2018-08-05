package ru.job4j.list;

/**
 * Class OwnQueue | Task Solution: Create Stack and Queue containers by using DynamicLinkedLlist container. [#160]
 * @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
 * @since 05.08.2018
 */
public class OwnQueue<E> {

    private int size = 0;
    private Node<E> firstNode;
    private Node<E> lastNode;

    /**
     * Add element to queue.
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
     * Poll first element from queue.
     * @return polled element.
     */
    public E poll() {
        if (this.size > 0) {
            E pollingElement = this.firstNode.element;
            this.firstNode = this.firstNode.next;
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
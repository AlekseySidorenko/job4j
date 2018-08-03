package ru.job4j.list;

/**
 * Class SimpleArrayList Task Solution: Create method delete for simply-connected list [#51424]
 * @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
 * @since 03.08.2018
 */
public class SimpleArrayList<E> {

    private int size = 0;
    private Node<E> first;

    /**
     * Add element to storage.
     * @param data element.
     */
    public void add(E data) {
        Node<E> newLink = new Node<>(data);
        newLink.next = this.first;
        this.first = newLink;
        this.size++;
    }

    /**
     * Delete first element from storage.
     * @return deleted element.
     */
    public E delete() {
        if (this.size > 0) {
            E deletingItem = this.get(0);
            this.first = this.first.next;
            this.size--;
            return deletingItem;
        }
        return null;
    }

    /**
     * Get element from storage by index.
     * @param index index.
     * @return element.
     */
    public E get(int index) {
        Node<E> result = this.first;
        if (index < size) {
            for (int i = 0; i < index; i++) {
                result = result.next;
            }
            return result.data;
        }
        return null;
    }

    /**
     * Get size of storage.
     * @return number of elements in storage.
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Class Node Task Solution: Create method delete for simply-connected list [#51424]
     * @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
     * @since 03.08.2018
     */
    private static class Node<E> {
        E data;
        Node<E> next;

        /**
         * Constructor.
         */
        Node(E data) {
            this.data = data;
        }
    }
}
package com.example.project_1.DataStructures;

import java.util.Iterator;

public class List<E> implements Iterable<E> {

    private Node<E> head, tail;
    private int size;

    public int size() {
        return size;
    }

    //Use it in the algorithm, so I don't need to copy the whole list every time
    //Note: this method is only used in the algorithm class to make the
    //O(n^3) become O(n^2) and not for always use
    public void copy(E data, List<E> list) {
        Node<E> node = new Node<>(data);
        node.setNext(list.head);

        if (list.isEmpty()) {
            head = node;
            tail = node;
            this.size = 1;
            return;
        }
        head = node;
        this.size = list.size() + 1;
    }


    public void insertFirst(E data) {
        Node<E> newNode = new Node<>(data);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.setNext(head);
            head = newNode;
        }
        size++;
    }

    public void insertLast(E data) {
        Node<E> newNode = new Node<>(data);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            tail.setNext(newNode);
            tail = newNode;
        }
        size++;
    }

    public E getFirst() {
        if (isEmpty())
            return null;
        return head.getData();
    }

    public E getLast() {
        if (isEmpty())
            return null;
        return tail.getData();
    }

    public E delete(E data) {

        if (isEmpty())
            return null;

        Node<E> curr = head, prev = null;

        //To find the Node that contains the data you want to delete
        while (curr != null) {
            if (curr.getData().equals(data)) {
                E deletedData = curr.getData();

                //To delete the node if there is only one element
                if (curr == head && curr == tail) {
                    clear();
                    return deletedData;
                }

                //To delete the node if it is the first element
                if (curr == head) {
                    head = curr.getNext();
                    size--;
                    return deletedData;
                }

                //To delete the node if it is the last element
                if (tail == curr) {
                    prev.setNext(null);
                    tail = prev;
                    size--;
                    return deletedData;
                }
                //To delete the node if it is in the middle of the list
                size--;
                prev.setNext(curr.getNext());
                return deletedData;
            }
            prev = curr;
            curr = curr.getNext();
        }
        return null;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public String toString() {
        if (isEmpty())
            return null;

        StringBuilder s = new StringBuilder();
        for (E curr : this) {
            s.append(curr.toString()).append(" ");
        }
        return s.toString();
    }

    @Override
    public Iterator<E> iterator() {
        return new IteratorImpl();
    }

    private class IteratorImpl implements Iterator<E> {

        Node<E> current = head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public E next() {
            E data = current.getData();
            current = current.getNext();
            return data;
        }
    }
}

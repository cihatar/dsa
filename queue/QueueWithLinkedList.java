package queue;

import java.util.LinkedList;

public class QueueWithLinkedList {
    private final LinkedList<Integer> linkedList;

    public QueueWithLinkedList(int size) {
        linkedList = new LinkedList<>();
    }

    public void enQueue(int value) {
        linkedList.addLast(value);
    }

    public void deQueue() {
        if (isEmpty()) return;
        linkedList.removeFirst();
    }

    public int peek() {
        if (isEmpty()) return -1;
        return (int) linkedList.getFirst();
    }

    public boolean isEmpty() {
        return linkedList.isEmpty();
    }

    public static void main(String[] args) {
        QueueWithLinkedList queue = new QueueWithLinkedList(6);

        queue.enQueue(1);
        queue.enQueue(2);
        queue.enQueue(3);
        queue.enQueue(4);
        queue.enQueue(5);
        queue.enQueue(6);

        System.out.println(queue.peek());

        queue.deQueue();
        queue.deQueue();

        System.out.println(queue.peek());
    }
}

package stack;

import java.util.LinkedList;

public class StackWithLinkedList {
    private final LinkedList<Integer> linkedList;

    public StackWithLinkedList() {
        linkedList = new LinkedList<>();
    }

    public void push(int value) {
        linkedList.addFirst(value);
    }

    public void pop() {
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
        StackWithLinkedList stack = new StackWithLinkedList();

        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);

        System.out.println(stack.peek());

        stack.pop();
        stack.pop();

        System.out.println(stack.peek());
    }
}
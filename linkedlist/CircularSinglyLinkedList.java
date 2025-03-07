package linkedlist;

class Node {
    public int value;
    public Node next;    
}

public class CircularSinglyLinkedList {
    private Node head;
    private Node tail;
    private int size;

    public CircularSinglyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public CircularSinglyLinkedList(int value) {
        Node node = new Node();
        node.next = node;
        node.value = value;
        head = node;
        tail = node;
        size = 1;
    }

    public void insert(int value, int location) {
        Node node = new Node();
        node.value = value;

        if (head == null) {
            node.next = node;
            head = node;
            tail = node;
        }
        else if (location == 0) {
            node.next = head;
            head = node;
            tail.next = head;
        }
        else if (location >= size) {
            tail.next = node;
            tail = node;
            tail.next = head;
        }
        else {
            Node temp = head;
            int index = 0;
            while (index < location - 1) {
                temp = temp.next;
                index++;
            }
            node.next = temp.next;
            temp.next = node;
        }
        size++;
    }

    public void delete(int location) {
        if (head == null) {
            return;
        }
        else if (location == 0) {
            head = head.next;
            tail.next = head;
            if (size == 1) {
                head.next = null;
                head = null;
                tail = null;
            }
        }
        else if (location >= size) {
            Node temp = head;
            for (int i = 0; i < size - 1; i++) {
                temp = temp.next;
            }
            if (temp == head) {
                head.next = head = tail = null;
            } else {
                temp.next = head;
                tail = temp;
            }
        } 
        else {
            Node temp = head;
            int index = 0;
            while (index < location - 1) {
                temp = temp.next;
                index++;
            }
            temp.next = temp.next.next;
        }
        size--;
    }

    public void traverse() {
        Node temp = head;
        for (int i = 0; i < size; i++) {
            System.out.print(temp.value + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    public boolean search(int value) {
        Node temp = head;
        for (int i = 0; i < size; i++) {
            if (temp.value == value) {
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    public static void main(String[] args) {
        CircularSinglyLinkedList csll = new CircularSinglyLinkedList();

        csll.insert(1, 0);
        csll.insert(2, 1);
        csll.insert(3, 2);
        csll.insert(4, 3);
        csll.insert(5, 4);

        System.out.println(csll.search(2));

        csll.traverse();

        csll.delete(6);

        csll.traverse();
    }   
}

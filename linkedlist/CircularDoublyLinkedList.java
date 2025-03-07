package linkedlist;

class Node {
    public int value;
    public Node next;    
    public Node prev;    
}

public class CircularDoublyLinkedList {
    private Node head;
    private Node tail;
    private int size;

    public CircularDoublyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }
    
    public CircularDoublyLinkedList(int value) {
        Node node = new Node();
        node.next = node;
        node.prev = node;
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
            node.prev = node;
            head = node;
            tail = node;
        }
        else if (location == 0) {
            node.next = head;
            node.prev = tail;
            head.prev = node;
            head = node;
            tail.next = node;
        }
        else if (location >= size) {
            node.next = head;
            node.prev = tail;
            tail.next = node;
            tail = node;
            head.prev = node;
        }
        else {
            Node temp = head;
            int index = 0;
            while (index < location - 1) {
                temp = temp.next;
                index++;
            }
            node.next = temp.next;
            node.prev = temp;
            temp.next = node;
            node.next.prev = node;
        }
        size++;
    }

    public void delete(int location) {
        if (head == null) {
            return;
        }
        else if (location == 0) {
            if (size == 1) {
                head.next = null;
                head.prev = null;
                head = null;
                tail = null;
            }
            else {
                head = head.next;
                head.prev = tail;
                tail.next = head;
            }
        }
        else if (location >= size) {
            Node temp = tail.prev;
            if (size == 1) {
                head.next = null;
                head.prev = null;
                head = null;
                tail = null;
            }
            else {
                temp.next = head;
                tail = temp;
                head.prev = tail;
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
            temp.next.prev = temp;
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

    public void traverseReverse() {
        Node temp = tail;
        for (int i = 0; i < size; i++) {
            System.out.print(temp.value + " ");
            temp = temp.prev;
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
        CircularDoublyLinkedList cdll = new CircularDoublyLinkedList();

        cdll.insert(1, 0);
        cdll.insert(2, 1);
        cdll.insert(3, 2);
        cdll.insert(4, 3);
        cdll.insert(5, 4);

        System.out.println(cdll.search(4));

        cdll.delete(2);

        cdll.traverse();
        cdll.traverseReverse();
    }
}

package linkedlist;

class Node {
    public int value;
    public Node next;    
}

public class SinglyLinkedList {
    private Node head;
    private Node tail;
    private int size;

    public SinglyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }
    
    public SinglyLinkedList(int value) {
        Node node = new Node();
        node.next = null;
        node.value = value;
        head = node;
        tail = node;
        size = 1;
    }

    public void insert(int value, int location) {
        Node node = new Node();
        node.value = value;

        if (head == null) {
            node.next = null;
            head = node;
            tail = node;
        }
        else if (location == 0) {
            node.next = head;
            head = node;
        }
        else if (location >= size) {
            node.next = null;
            tail.next = node;
            tail = node;
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
            if (size == 1) {
                tail = null;
            }
        }
        else if (location >= size) {
            Node temp = head;
            for (int i = 0; i < size - 1; i++) {
                temp = temp.next;
            }
            if (temp == head) {
                tail = head = null;
            } else {
                temp.next = null;
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
        while (temp != null) {
            System.out.print(temp.value + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    public boolean search(int value) {
        Node temp = head;
        while (temp != null) {
            if (temp.value == value) {
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    public static void main(String[] args) {
        SinglyLinkedList sll = new SinglyLinkedList();

        sll.insert(1, 0);
        sll.insert(2, 1);
        sll.insert(3, 2);
        sll.insert(4, 3);
        sll.insert(5, 4);

        System.out.println(sll.search(10));

        sll.delete(4);

        sll.traverse();
    }
}

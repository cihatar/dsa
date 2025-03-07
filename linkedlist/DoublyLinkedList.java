package linkedlist;

class Node {
    public int value;
    public Node next;    
    public Node prev;    
}

public class DoublyLinkedList {
    private Node head;
    private Node tail;
    private int size;

    public DoublyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }
    
    public DoublyLinkedList(int value) {
        Node node = new Node();
        node.next = null;
        node.prev = null;
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
            node.prev = null;
            head = node;
            tail = node;
        }
        else if (location == 0) {
            node.next = head;
            node.prev = null;
            head.prev = node;
            head = node;
        }
        else if (location >= size) {
            node.next = null;
            node.prev = tail;
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
                head = null;
                tail = null;
            }
            else {
                head = head.next;
                head.prev = null;
            }
        }
        else if (location >= size) {
            Node temp = tail.prev;
            if (size == 1) {
                head = null;
                tail = null;
            }
            else {
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
            temp.next.prev = temp;
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

    public void traverseReverse() {
        Node temp = tail;
        while (temp != null) {
            System.out.print(temp.value + " ");
            temp = temp.prev;
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
        DoublyLinkedList dll = new DoublyLinkedList();

        dll.insert(1, 0);
        dll.insert(2, 1);
        dll.insert(3, 2);
        dll.insert(4, 3);
        dll.insert(5, 4);

        System.out.println(dll.search(4));

        dll.delete(0);

        dll.traverse();
        dll.traverseReverse();
    }
}

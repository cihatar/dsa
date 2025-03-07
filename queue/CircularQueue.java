package queue;

public class CircularQueue {
    private int[] arr;
    private int first;
    private int top;
    private int size;

    public CircularQueue(int size) {
        this.arr = new int[size];
        this.first = this.top = -1;
        this.size = size;
    }

    public void enQueue(int value) {
        if (isFull()) {
            return;
        }
        else if (isEmpty()) {
            first = 0;
            top++;
            arr[top] = value;
        }
        else {
            // if (top == size - 1) {
            //     top = 0;
            // } else {
            //     top++;
            // }
            // arr[top] = value;
            top = (top + 1) % size;
            arr[top] = value;
        }
    }

    public int deQueue() {
        if (isEmpty()) {
            return -1;
        }
        else {
            int result = arr[first];
            if (first == top) {
                first = top = -1;
            }
            else if (first == size - 1) {
                first = 0;
            }
            else {
                first++;
            }
            return result;
        }
    }

    public int peek() {
        if (isEmpty()) {
            return -1;
        } 
        return arr[first];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return (first == 0 && top == size - 1) || (top + 1 == first);
    }

    public static void main(String[] args) {
        CircularQueue queue = new CircularQueue(6);

        System.out.println(queue.isEmpty());

        queue.enQueue(1);
        queue.enQueue(2);
        queue.enQueue(3);
        queue.enQueue(4);
        queue.enQueue(5);
        queue.enQueue(6);

        System.out.println(queue.deQueue());
        System.out.println(queue.deQueue());

        System.out.println(queue.isEmpty());

        System.out.println(queue.peek());
    }
}

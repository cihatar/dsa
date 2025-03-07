package queue;

public class QueueWithArray {
    private int[] arr;
    private int first;
    private int top;

    public QueueWithArray(int size) {
        this.arr = new int[size];
        this.first = this.top = -1;
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
            top++;
            arr[top] = value;
        }
    }

    public int deQueue() {
        if (isEmpty()) {
            return -1;
        } 
        else {
            int result = arr[first];
            first++;
            if (first > top) {
                first = -1;
                top = -1;
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
        return first == -1 || first == arr.length;
    }

    public boolean isFull() {
        return top == arr.length - 1;
    }

    public static void main(String[] args) {
        QueueWithArray queue = new QueueWithArray(6);

        System.out.println(queue.isEmpty());

        queue.enQueue(1);
        queue.enQueue(2);
        queue.enQueue(3);
        queue.enQueue(4);

        System.out.println(queue.deQueue());
        System.out.println(queue.deQueue());

        System.out.println(queue.isEmpty());

        System.out.println(queue.peek());
    }
}

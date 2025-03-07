package stack;

public class StackWithArray {
    private int[] arr;
    private int top;

    public StackWithArray(int size) {
        arr = new int[size];
        top = -1;
    }

    public void push(int value) {
        if (isFull()) return;
        arr[++top] = value;
    }

    public void pop() {
        if (isEmpty()) return;
        top--;
    }

    public int peek() {
        if (isEmpty()) return -1;
        return arr[top];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == arr.length - 1;
    }

    public static void main(String[] args) {
        StackWithArray stack = new StackWithArray(5);

        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);

        System.out.println(stack.peek());

        System.out.println(stack.isFull());

        stack.pop();
        stack.pop();

        System.out.println(stack.peek());

        System.out.println(stack.isFull());
    }
}
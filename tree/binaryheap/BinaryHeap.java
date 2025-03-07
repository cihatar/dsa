package tree.binaryheap;

public class BinaryHeap {
    private int[] arr;
    private int size;

    public BinaryHeap(int size) {
        arr = new int[size + 1];
        this.size = 0;
    }

    public void levelOrder() {
        for (int i = 1; i <= size; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    private void heapifyBottomToTop(int index, String heapType) {
        if (index <= 1) {
            return;
        }
        int parent = index / 2;
        // for min heap
        if ("min".equals(heapType)) {
            if (arr[index] < arr[parent]) {
                int temp = arr[index];
                arr[index] = arr[parent];
                arr[parent] = temp; 
            }
        }
        // for max heap
        else if ("max".equals(heapType)) {
            if (arr[index] > arr[parent]) {
                int temp = arr[index];
                arr[index] = arr[parent];
                arr[parent] = temp; 
            }
        }
        heapifyBottomToTop(parent, heapType);
    }

    private void heapifyTopToBottom(int index, String heapType) {
        int left = 2 * index;
        int right = 2 * index + 1;
        int swapChild = 0;
        if (size < left) {
            return;
        }
        // for min heap
        if ("min".equals(heapType)) {
            // has one child
            if (size == left) {
                if (arr[index] > arr[left]) {
                    int temp = arr[index];
                    arr[index] = arr[left];
                    arr[left] = temp; 
                }
                return;
            }
            // has two children
            else {
                swapChild = arr[left] > arr[right] ? right : left;
                if (arr[index] > arr[swapChild]) {
                    int temp = arr[index];
                    arr[index] = arr[swapChild];
                    arr[swapChild] = temp; 
                }
            }
        }
        // for max heap
        else if ("max".equals(heapType)) {
            // has one child
            if (size == left) {
                if (arr[index] < arr[left]) {
                    int temp = arr[index];
                    arr[index] = arr[left];
                    arr[left] = temp; 
                }
                return;
            }
            // has two children
            else {
                swapChild = arr[left] > arr[right] ? left : right;
                if (arr[index] < arr[swapChild]) {
                    int temp = arr[index];
                    arr[index] = arr[swapChild];
                    arr[swapChild] = temp; 
                }
            }
        }
        heapifyTopToBottom(swapChild, heapType);
    }

    public void insert(int value, String heapType) {
        if (isFull()) {
            return;
        }
        arr[++size] = value;
        heapifyBottomToTop(size, heapType);
    }

    public int extract(String heapType) {
        if (isEmpty()) {
            return -1;
        }
        int extractedValue = arr[1];
        arr[1] = arr[size];
        size--;
        heapifyTopToBottom(1, heapType);
        return extractedValue;
    }

    public Integer peek() {
        return isEmpty() ? null : arr[1];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return arr.length == size + 1;
    }

    public static void main(String[] args) {
        BinaryHeap heap = new BinaryHeap(10);

        heap.insert(5, "min");
        heap.insert(10, "min");
        heap.insert(15, "min");
        heap.insert(8, "min");
        heap.insert(25, "min");
        heap.insert(30, "min");

        heap.levelOrder();

        heap.extract("min");

        heap.levelOrder();
    }
}

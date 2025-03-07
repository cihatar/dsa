package sorting;

import tree.binaryheap.BinaryHeap;

public class HeapSort {
    private int[] arr;

    public HeapSort(int[] arr) {
        this.arr = arr;
    }

    public void sort() {
        BinaryHeap heap = new BinaryHeap(arr.length);
        for (int i = 0; i < arr.length; i++) {
            heap.insert(arr[i], "min");
        }

        for (int i = 0; i < arr.length; i++) {
            arr[i] = heap.extract("min");
        }
    }

    public void print() {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    public static void main(String[] args) {
        int[] arr = { 23, 12, 9, 32, 2, 55, 5 };
        HeapSort heap = new HeapSort(arr);
        heap.sort();
        heap.print();
    }
}

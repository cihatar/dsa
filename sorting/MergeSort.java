package sorting;

public class MergeSort {

    public static void sort(int[] arr) {
        if (arr.length <= 1) {
            return;
        }
        int arrLength = arr.length;
        int midIndex = arrLength / 2;
        int[] leftHalf = new int[midIndex]; 
        int[] rightHalf = new int[arrLength - midIndex]; 

        for (int i = 0; i < midIndex; i++) {
            leftHalf[i] = arr[i];
        }
        for (int i = midIndex; i < arrLength; i++) {
            rightHalf[i - midIndex] = arr[i];
        }

        sort(leftHalf);
        sort(rightHalf);
        merge(arr, leftHalf, rightHalf);
    }
    
    private static void merge(int[] arr, int[] leftHalf, int[] rightHalf) {
        int leftLength = leftHalf.length;
        int rightLength = rightHalf.length;

        int i = 0, j = 0, k = 0;

        while (i < leftLength && j < rightLength) {
            if (leftHalf[i] <= rightHalf[j]) {
                arr[k] = leftHalf[i];
                i++;
            }
            else {
                arr[k] = rightHalf[j];
                j++;
            }
            k++;
        }

        while (i < leftLength) {
            arr[k] = leftHalf[i];
            i++; k++;
        }
        while (j < rightLength) {
            arr[k] = rightHalf[j];
            j++; k++;
        }
    }

    public static void print(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    public static void main(String[] args) {
        int[] arr = { 23, 12, 9, 32, 2, 55, 5 };
        MergeSort.sort(arr);
        MergeSort.print(arr);
    }
}

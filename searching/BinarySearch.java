package searching;

public class BinarySearch {

    public static int search(int[] arr, int value) {
        int start = 0;
        int end = arr.length - 1;
        int middle = (start + end) / 2;

        while (arr[middle] != value && start <= end) {
            if (value < arr[middle]) {
                end = middle - 1;
            }
            else {
                start = middle + 1;
            }
            middle = (start + end) / 2;
        }

        return arr[middle] == value ? middle : -1;
    }
    public static void main(String[] args) {
        int[] arr = { 1, 2, 3, 4, 5, 6, 7, 8 };
        System.out.println(BinarySearch.search(arr, 7));
    }
}

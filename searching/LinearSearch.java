package searching;

public class LinearSearch {

    public static int search(int[] arr, int value) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value) {
                return i;
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        int[] arr = { 1, 3, 2, 10, 24, 11 };
        System.out.println(LinearSearch.search(arr, 4));
    }
}

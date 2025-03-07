// Number of buckets - round(sqrt(number of elements))
// Appropriate bucket - ceil(value * number of buckets / max value in the array)

package sorting;

import java.util.ArrayList;
import java.util.Collections;

public class BucketSort {
    private int arr[];

    public BucketSort(int arr[]) {
        this.arr = arr;
    }

    public void sort() {
        int numberOfBuckets = (int) Math.ceil(Math.sqrt(arr.length));
        int maxValue = Integer.MIN_VALUE;
        for (int value : arr) {
            if (value > maxValue) {
                maxValue = value;
            }
        }
        ArrayList<Integer>[] buckets = new ArrayList[numberOfBuckets];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<>();
        }
        for (int value : arr) {
            int bucketNumber = (int) Math.ceil(((float)value * numberOfBuckets) / (float)maxValue );
            buckets[bucketNumber - 1].add(value);
        }

        printBucket(buckets);

        for (ArrayList<Integer> bucket : buckets) {
            Collections.sort(bucket);
        }

        printBucket(buckets);

        int index = 0;
        for (ArrayList<Integer> bucket : buckets) {
            for (int value : bucket) {
                arr[index] = value;
                index++;
            }
        }
    }

    public void printBucket(ArrayList<Integer>[] buckets) {
        for (int i = 0; i < buckets.length; i++) {
            System.out.println("\nBucket " + i + ":");
            for (int j = 0; j < buckets[i].size(); j++) {
                System.out.print(buckets[i].get(j) + " ");
            }
        }
    }

    public void print(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    public static void main(String[] args) {
        int[] arr = { 9, 7, 5, 4, 2, 1, 3, 6, 8 };
        BucketSort bucket = new BucketSort(arr);
        bucket.sort();
    }
}

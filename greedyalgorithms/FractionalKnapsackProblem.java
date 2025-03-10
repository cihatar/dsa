package greedyalgorithms;

import java.util.*;

class KnapsackItem {
    public int index;
    public int value;
    public int weight;
    public double ratio;

    public KnapsackItem(int index, int value, int weight) {
        this.index = index;
        this.value = value;
        this.weight = weight;
        this.ratio = value * 1.0 / weight;
    }

    @Override
    public String toString() {
        return "Item index: " + index + ", Value: " + value + ", Weight: " + weight + ", Ratio: " + ratio;
    }
}

public class FractionalKnapsackProblem {

    public static void knapSack(ArrayList<KnapsackItem> knapsackItems, int capacity) {
        // sort items by ratio in descending order
        Comparator<KnapsackItem> comparator = (KnapsackItem k1, KnapsackItem k2) -> {
            return k2.ratio > k1.ratio ? 1 : -1;
        };

        Collections.sort(knapsackItems, comparator);

        int usedCapacity = 0;
        double totalValue = 0;

        for (KnapsackItem item : knapsackItems) {
            if (usedCapacity + item.weight <= capacity) {
                usedCapacity = usedCapacity + item.weight;
                System.out.println("Taken: " + item);
                totalValue = totalValue + item.value;
            }
            else {
                int usedWeight = capacity - usedCapacity;
                double value = item.ratio * usedWeight;
                System.out.println("Taken: Item index: " + item.index + ", Obtained value: " + value + ", Used weight: " + usedWeight + ", Ratio: " + item.ratio);
                usedCapacity = usedCapacity + usedWeight;
                totalValue = totalValue + value;
            }

            if (capacity == usedCapacity) break;
        }

        System.out.println("Total value obtained: " + totalValue);
    }

    public static void main(String[] args) {
        ArrayList<KnapsackItem> knapsackItems = new ArrayList<>();

        int[] value = { 100, 120, 60 };
        int[] weight = { 20, 30, 10 };
        int capacity = 50;

        for (int i = 0; i < value.length; i++) {
            knapsackItems.add(new KnapsackItem(i+1, value[i], weight[i]));
        }

        FractionalKnapsackProblem.knapSack(knapsackItems, capacity);
    }
}

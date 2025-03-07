package hashing;

import java.util.ArrayList;

public class QuadraticProbing {
    private String[] hashTable;
    private int usedCellCount;

    public QuadraticProbing(int size) {
        hashTable = new String[size];
        usedCellCount = 0;
    }

    public int modASCIIHashFunction(String word, int cellCount) {
        char ch[] = word.toCharArray();
        int i, sum;
        for (sum=0, i=0; i < word.length(); i++) {
            sum += ch[i];
        }
        return sum % cellCount;
    }

    public double getLoadFactor() {
        return usedCellCount * 1.0 / hashTable.length;
    }

    public void rehash(String word) {
        ArrayList<String> data = new ArrayList<>();
        usedCellCount = 0;
        for (String s : hashTable) {
            if (s != null) {
                data.add(s);
            }
        }
        data.add(word);
        hashTable = new String[hashTable.length * 2];
        for (String s : data) {
            insert(s);
        }
    }

    public void insert(String word) {
        double loadFactor = getLoadFactor();
        if (loadFactor >= 0.75) {
            rehash(word);
        }
        else {
            int index = modASCIIHashFunction(word, hashTable.length);
            int counter = 0;
            for (int i = index; i < index + hashTable.length; i++) {
                int newIndex = (index + (counter * counter)) % hashTable.length;
                if (hashTable[newIndex] == null) {
                    hashTable[newIndex] = word;
                    break;
                }
                counter++;
            }
            usedCellCount++;
        }
    }

    public void delete(String word) {
        int index = modASCIIHashFunction(word, hashTable.length);
        int counter = 0;
        for (int i = index; i < index + hashTable.length; i++) {
            int newIndex = (index + (counter * counter)) % hashTable.length;
            if (hashTable[newIndex] != null && hashTable[newIndex].equals(word)) {
                hashTable[newIndex] = null;
                usedCellCount--;
            }
            counter++;
        }
    }

    public boolean search(String word) {
        int index = modASCIIHashFunction(word, hashTable.length);
        int counter = 0;
        for (int i = index; i < index + hashTable.length; i++) {
            int newIndex = (index + (counter * counter)) % hashTable.length;
            if (hashTable[newIndex] != null && hashTable[newIndex].equals(word)) {
                return true;
            }
            counter++;
        }
        return false;
    }

    public void traverse() {
        for (int i = 0; i < hashTable.length; i++) {
            System.out.println(i + " - " + hashTable[i]);
        }
    }

    public static void main(String[] args) {
        QuadraticProbing hashTable = new QuadraticProbing(2);

        hashTable.insert("the");
        hashTable.insert("quick");
        hashTable.insert("brown");
        hashTable.insert("fox");
        hashTable.insert("over");

        hashTable.traverse();
        System.out.println(hashTable.search("the"));

        hashTable.delete("the");

        hashTable.traverse();
        System.out.println(hashTable.search("the"));
    }
}

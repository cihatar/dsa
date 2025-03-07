package hashing;

import java.util.ArrayList;

public class DoubleHashing {
    private String[] hashTable;
    private int usedCellCount;

    public DoubleHashing(int size) {
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

    public int secondHashFunction(String word, int cellCount) {
        char ch[] = word.toCharArray();
        int i, sum;
        for (sum=0, i=0; i < word.length(); i++) {
            sum += ch[i];
        }
        while (sum > cellCount) {
            sum = addAllDigits(sum);
        }
        return sum % cellCount;
    }

    private int addAllDigits(int sum) {
        int value = 0;
        while (sum > 0) {
            value += sum % 10;
            sum = sum / 10;
        }
        return value;
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
            int x = modASCIIHashFunction(word, hashTable.length);
            int y = secondHashFunction(word, hashTable.length);
            for (int i = 0; i < hashTable.length; i++) {
                int newIndex = (x + i*y) % hashTable.length;
                if (hashTable[newIndex] == null) {
                    hashTable[newIndex] = word;
                    break;
                }
            }
            usedCellCount++;
        }
    }

    public void delete(String word) {
        int x = modASCIIHashFunction(word, hashTable.length);
        int y = secondHashFunction(word, hashTable.length);
        for (int i = 0; i < hashTable.length; i++) {
            int newIndex = (x + i*y) % hashTable.length;
            if (hashTable[newIndex] != null && hashTable[newIndex].equals(word)) {
                hashTable[newIndex] = null;
                usedCellCount--;
            }
        }
    }

    public boolean search(String word) {
        int x = modASCIIHashFunction(word, hashTable.length);
        int y = secondHashFunction(word, hashTable.length);
        for (int i = 0; i < hashTable.length; i++) {
            int newIndex = (x + i*y) % hashTable.length;
            if (hashTable[newIndex] != null && hashTable[newIndex].equals(word)) {
                return true;
            }
        }
        return false;
    }

    public void traverse() {
        for (int i = 0; i < hashTable.length; i++) {
            System.out.println(i + " - " + hashTable[i]);
        }
    }

    public static void main(String[] args) {
        DoubleHashing hashTable = new DoubleHashing(10);

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

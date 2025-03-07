package hashing;

import java.util.LinkedList;

public class DirectChaining {
    private LinkedList<String>[] hashTable;

    public DirectChaining(int size) {
        hashTable = new LinkedList[size];
    }

    public int modASCIIHashFunction(String word, int cellCount) {
        char ch[] = word.toCharArray();
        int i, sum;
        for (sum=0, i=0; i < word.length(); i++) {
            sum += ch[i];
        }
        return sum % cellCount;
    }

    public void insert(String word) {
        int index = modASCIIHashFunction(word, hashTable.length);
        if (hashTable[index] == null) {
            hashTable[index] = new LinkedList<String>();
            hashTable[index].add(word);
        }
        else {
            hashTable[index].add(word);
        }
    }

    public void delete(String word) {
        int index = modASCIIHashFunction(word, hashTable.length);
        boolean result = search(word);
        if (result == true) {
            hashTable[index].remove(word);
        }
    }

    public boolean search(String word) {
        int index = modASCIIHashFunction(word, hashTable.length);
        if (hashTable[index] != null && hashTable[index].contains(word)) {
            return true;
        }
        return false;
    }

    public void traverse() {
        for (int i = 0; i < hashTable.length; i++) {
            System.out.println(i + " - " + hashTable[i]);
        }
    }

    public static void main(String[] args) {
        DirectChaining hashTable = new DirectChaining(10);

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

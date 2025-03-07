package tree.trie;

import java.util.HashMap;
import java.util.Map;

class Node {
    Map<Character, Node> children;
    boolean endOfString;

    public Node() {
        children = new HashMap<>();
        endOfString = false;
    }
}

public class Trie {
    private Node root;

    public Trie() {
        root = new Node();
    }

    public void insert(String word) {
        Node current = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i); 
            Node node = current.children.get(ch);
            if (node == null) {
                node = new Node();
                current.children.put(ch, node);
            }
            current = node;
        }
        current.endOfString = true;
    }

    private boolean delete(Node parent, String word, int index) {
        char ch = word.charAt(index);
        Node current = parent.children.get(ch);
        boolean canBeDeleted;

        // CASE 1: some other prefix of string is same as the one that we want to delete (API, APPLE)
        if (current.children.size() > 1) {
            delete(current, word, index + 1);
            return false;
        }
        // CASE 2: the string is a prefix of another string (API, APIS)
        if (index == word.length() - 1) {
            if (current.children.size() >= 1) {
                current.endOfString = false;
                return false;
            }
            else {
                parent.children.remove(ch);
                return true;
            }
        }
        // CASE 3: Other string is a prefix of this string (APIS, AP)
        if (current.endOfString == true) {
            delete(current, word, index + 1);
            return false;
        }

        // CASE 4: not any node depends on this string (K)
        canBeDeleted = delete(current, word, index + 1);
        if (canBeDeleted == true) {
            parent.children.remove(ch);
            return true;
        }
        else {
            return false;
        }
    }

    public void delete(String word) {
        if (search(word) == true) {
            delete(root, word, 0);
        }
    }

    public boolean search(String word) {
        Node current = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            Node node = current.children.get(ch);
            if (node == null) {
                return false;
            }
            current = node;
        }
        if (current.endOfString != true) {
            return false;
        }
        return true;
    }
    
    public static void main(String[] args) {
        Trie trie = new Trie();

        trie.insert("app");
        trie.insert("api");

        System.out.println(trie.search("app"));
        System.out.println(trie.search("api"));
        System.out.println(trie.search("apis"));

        trie.delete("app");

        System.out.println(trie.search("app"));
    }
}

// Full Binary Tree - every node has 0 or 2 children
// Complete Binary Tree - all the levels are completely filled except possibly the last level and the last level has all keys as left as possible
// Perfect Binary Tree - all the internal nodes have two children and all leaf nodes are at the same level
// Balanced Binary Tree - the height of the tree is O(log n) where n is the number of nodes

package tree.binarytree;

import java.util.LinkedList;
import java.util.Queue;

class Node {
    public String value;
    public Node left;
    public Node right;
}

public class BinaryTreeWithLinkedList {
    public Node root;

    public BinaryTreeWithLinkedList() {
        root = null;
    }

    public void preOrder(Node root) {
        if (root == null) {
            return;
        }
        System.out.print(root.value + " ");
        preOrder(root.left);
        preOrder(root.right);
    }

    public void inOrder(Node root) {
        if (root == null) {
            return;
        }
        inOrder(root.left);
        System.out.print(root.value + " ");
        inOrder(root.right);
    }

    public void postOrder(Node root) {
        if (root == null) {
            return;
        }
        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root.value + " ");
    }

    public void levelOrder() {
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node presentNode = queue.remove();
            System.out.print(presentNode.value + " ");
            if (presentNode.left != null) {
                queue.add(presentNode.left);
            }
            if (presentNode.right != null) {
                queue.add(presentNode.right);
            }
        }
    }

    public boolean search(String value) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node presentNode = queue.remove();
            if (presentNode.value.equals(value)) {
                return true;
            }
            if (presentNode.left != null) {
                queue.add(presentNode.left);
            }
            if (presentNode.right != null) {
                queue.add(presentNode.right);
            }
        }
        return false;
    }

    public void insert(String value) {
        Node newNode = new Node();
        newNode.value = value;
        newNode.left = newNode.right = null;

        if (root == null) {
            root = newNode;
            return;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node presentNode = queue.remove();
            if (presentNode.left == null) {
                presentNode.left = newNode;
                break;
            }
            else if (presentNode.right == null) {
                presentNode.right = newNode;
                break;
            }
            else {
                queue.add(presentNode.left);
                queue.add(presentNode.right);
            }
        }
    }

    private Node getDeepestNode() {
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        Node presentNode = null;
        while (!queue.isEmpty()) {
            presentNode = queue.remove();
 
            if (presentNode.left != null) {
                queue.add(presentNode.left);
            }
            if (presentNode.right != null) {
                queue.add(presentNode.right);
            }
        }
        return presentNode;
    }

    private void deleteDeepestNode() {
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        Node previousNode, presentNode = null;
        while (!queue.isEmpty()) {
            previousNode = presentNode;
            presentNode = queue.remove();
 
            if (presentNode.left == null) {
                previousNode.right = null;
                return;
            }
            else if (presentNode.right == null) {
                presentNode.left = null;
                return;
            }
            else {
                queue.add(presentNode.left);
                queue.add(presentNode.right);
            }
        }
    }

    public void delete(String value) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node presentNode = queue.remove();
            if (presentNode.value.equals(value)) {
                presentNode.value = getDeepestNode().value;
                deleteDeepestNode();
                break;
            }
            if (presentNode.left != null) {
                queue.add(presentNode.left);
            }
            if (presentNode.right != null) {
                queue.add(presentNode.right);
            }
        }
    }

    public static void main(String[] args) {
        BinaryTreeWithLinkedList tree = new BinaryTreeWithLinkedList();

        tree.insert("n1");
        tree.insert("n2");
        tree.insert("n3");
        tree.insert("n4");
        tree.insert("n5");
        tree.insert("n6");
        tree.insert("n7");
        tree.insert("n8");
        tree.insert("n9");

        tree.levelOrder();

        System.out.println(tree.search("n10"));

        tree.delete("n6");

        tree.levelOrder();
    }
}   

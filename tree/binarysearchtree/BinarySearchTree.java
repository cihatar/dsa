package tree.binarysearchtree;

import java.util.LinkedList;
import java.util.Queue;

class Node {
    public int value;
    public Node left;
    public Node right;
}

public class BinarySearchTree {
    public Node root;

    public BinarySearchTree() {
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

    private boolean search(int value, Node root) {
        if (root == null) {
            return false;
        }
        else if (root.value == value) {
            return true;
        }
        else if (root.value > value) {
            return search(value, root.left);
        }
        else {
            return search(value, root.right);
        }     
    }

    private Node insertNode(int value, Node root) {
        if (root == null) {
            Node newNode = new Node();
            newNode.value = value;
            return newNode;
        }
        else if (root.value > value) {
            root.left = insertNode(value, root.left);
        }
        else {
            root.right = insertNode(value, root.right);
        }     
        return root;
    }

    private Node minimumNode(Node root) {
        if (root.left == null) {
            return root;
        }
        else {
            return minimumNode(root.left);
        }
    }

    private Node deleteNode(int value, Node root) {
        if (root == null) {
            return null;
        }
        if (root.value > value) {
            root.left = deleteNode(value, root.left);
        }
        else if (root.value < value) {
            root.right = deleteNode(value, root.right);
        }   
        else {
            // has two children
            if (root.left != null && root.right != null) {
                Node temp = root;
                Node minNodeForRight = minimumNode(temp.right);
                root.value = minNodeForRight.value;
                root.right = deleteNode(minNodeForRight.value, root.right);
            }
            // has one child
            else if (root.left != null) {
                root = root.left;
                root.left = null;
            }
            else if (root.right != null) {
                root = root.right;
                root.right = null;
            }
            // has no children
            else {
                root = null;
            }
        }
        return root;
    }

    public boolean search(int value) {
        return search(value, root);
    }

    public void insert(int value) {
        root = insertNode(value, root);
    }

    public void delete(int value) {
        root = deleteNode(value, root);
    }

    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();

        tree.insert(20);
        tree.insert(10);
        tree.insert(30);
        tree.insert(5);
        tree.insert(8);
        tree.insert(25);
        tree.insert(50);

        tree.levelOrder();

        System.out.println(tree.search(10));

        tree.delete(20);

        tree.levelOrder();
    }
}

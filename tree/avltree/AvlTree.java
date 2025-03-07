package tree.avltree;

import java.util.LinkedList;
import java.util.Queue;

class Node {
    public int value;
    public Node left;
    public Node right;
    public int height;

    public Node() {
        height = 0;
    }
}

public class AvlTree {
    public Node root;

    public AvlTree() {
        root = null;
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

    public int getHeight(Node node) {
        return node == null ? 0 : node.height;
    }

    private Node rotateRight(Node disbalancedNode) {
        Node newRoot = disbalancedNode.left;
        disbalancedNode.left = disbalancedNode.left.right;
        newRoot.right = disbalancedNode;
        disbalancedNode.height = 1 + Math.max(getHeight(disbalancedNode.left), getHeight(disbalancedNode.right));
        newRoot.height = 1 + Math.max(getHeight(newRoot.left), getHeight(newRoot.right));
        return newRoot;
    } 
    
    private Node rotateLeft(Node disbalancedNode) {
        Node newRoot = disbalancedNode.right;
        disbalancedNode.right = disbalancedNode.right.left;
        newRoot.left = disbalancedNode;
        disbalancedNode.height = 1 + Math.max(getHeight(disbalancedNode.left), getHeight(disbalancedNode.right));
        newRoot.height = 1 + Math.max(getHeight(newRoot.left), getHeight(newRoot.right));
        return newRoot;
    } 

    private int getBalance(Node node) {
        return node == null ? 0 : getHeight(node.left) - getHeight(node.right);
    }

    private Node insertNode(int value, Node node) {
        if (node == null) {
            Node newNode = new Node();
            newNode.value = value;
            newNode.height = 1;
            return newNode;
        }
        else if (node.value > value) {
            node.left = insertNode(value, node.left);
        }
        else {
            node.right = insertNode(value, node.right);
        }     

        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        int balance = getBalance(node);

        if (balance > 1 && value < node.left.value) {
            return rotateRight(node);
        }
        if (balance > 1 && value > node.left.value) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }
        if (balance < -1 && value > node.right.value) {
            return rotateLeft(node);
        }
        if (balance < -1 && value < node.left.value) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        return node;
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

        int balance = getBalance(root);

        if (balance > 1 && getBalance(root.left) >= 0) {
            return rotateRight(root);
        }
        if (balance > 1 && getBalance(root.left) < 0) {
            root.left = rotateLeft(root.left);
            return rotateRight(root);
        }
        if (balance < -1 && getBalance(root.right) <= 0) {
            return rotateLeft(root);
        }
        if (balance < -1 && getBalance(root.right) > 0) {
            root.right = rotateRight(root.right);
            return rotateLeft(root);
        }

        return root;
    }

    public void insert(int value) {
        root = insertNode(value, root);
    }

    public void delete(int value) {
        root = deleteNode(value, root);
    }

    public static void main(String[] args) {
        AvlTree tree = new AvlTree();

        tree.insert(5);
        tree.insert(10);
        tree.insert(15);
        tree.insert(20);

        tree.levelOrder();
    }
}

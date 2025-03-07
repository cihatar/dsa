// left child - arr[2x]
// right child - arr[2x+1]

package tree.binarytree;

public class BinaryTreeWithArray {
    public String[] arr;
    public int lastUsedIndex;

    public BinaryTreeWithArray(int size) {
        arr = new String[size + 1];
        lastUsedIndex = 0;
    }

    public void preOrder(int index) {
        if (index > lastUsedIndex) {
            return;
        }
        System.out.print(arr[index] + " ");
        preOrder(2 * index);
        preOrder(2 * index + 1);
    }

    public void inOrder(int index) {
        if (index > lastUsedIndex) {
            return;
        }
        inOrder(2 * index);
        System.out.print(arr[index] + " ");
        inOrder(2 * index + 1);
    }

    public void postOrder(int index) {
        if (index > lastUsedIndex) {
            return;
        }
        postOrder(2 * index);
        postOrder(2 * index + 1);
        System.out.print(arr[index] + " ");
    }

    public void levelOrder() {
        for (int i = 1; i <= lastUsedIndex; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    public boolean search(String value) {
        for (int i = 1; i <= lastUsedIndex; i++) {
            if (arr[i].equals(value)) {
                return true;
            }
        }
        return false;
    }

    public void insert(String value) {
        if (isFull()) {
            return;
        }
        arr[++lastUsedIndex] = value;
    }

    public void delete(String value) {
        if (isEmpty()) {
            return;
        }
        for (int i = 1; i <= lastUsedIndex; i++) {
            if (arr[i].equals(value)) {
                arr[i] = arr[lastUsedIndex];
                lastUsedIndex--;
            }
        }
    }

    public boolean isEmpty() {
        return lastUsedIndex == 0;
    }

    public boolean isFull() {
        return arr.length - 1 == lastUsedIndex;
    }

    public static void main(String[] args) {
        BinaryTreeWithArray tree = new BinaryTreeWithArray(10);

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

        System.out.println(tree.search("n5"));

        tree.delete("n5");

        tree.levelOrder();
    }
}

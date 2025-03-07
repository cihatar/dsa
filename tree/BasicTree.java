package tree;

import java.util.ArrayList;

class TreeNode {
    public String data;
    public ArrayList<TreeNode> children;

    public TreeNode(String data) {
        this.data = data;
        this.children = new ArrayList<>();
    }

    public void addChild(TreeNode node) {
        children.add(node);
    }

    public String print(int level) {
        String ret;
        ret = "  ".repeat(level) + data + "\n";
        for (TreeNode node : children) {
            ret += node.print(level + 1);
        }
        return ret;
    }
}

public class BasicTree {
    public static void main(String[] args) {
        TreeNode drinks = new TreeNode("Drinks");
        TreeNode hot = new TreeNode("Hot");
        TreeNode cold = new TreeNode("Cold");

        drinks.addChild(hot);
        drinks.addChild(cold);

        TreeNode tea = new TreeNode("Tea");
        TreeNode coffee = new TreeNode("Coffee");
        TreeNode nonalcohol = new TreeNode("Nonalcohol");
        TreeNode alcohol = new TreeNode("Alcohol");

        hot.addChild(tea);
        hot.addChild(coffee);
        cold.addChild(nonalcohol);
        cold.addChild(alcohol);

        System.out.println(drinks.print(0));
    }
}

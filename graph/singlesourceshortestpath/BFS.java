package graph.singlesourceshortestpath;

import java.util.*;

class Node {
    public String name;
    public int index;
    public ArrayList<Node> neighbors;
    public boolean isVisited;
    public Node parent;

    public Node(String name, int index) {
        this.name = name;
        this.index = index;
        this.neighbors = new ArrayList<>();
        this.isVisited = false;
        this.parent = null;
    }
}

public class BFS  {
    private ArrayList<Node> nodeList;

    public BFS(ArrayList<Node> nodeList) {
        this.nodeList = nodeList;
    }

    public void addUndirectedEdge(int i, int j) {
        Node first = nodeList.get(i);
        Node second = nodeList.get(j);
        first.neighbors.add(second);
        second.neighbors.add(first);
    }

    public void bfsMethod(Node node) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        while (!queue.isEmpty()) {
            Node currentNode = queue.remove();
            currentNode.isVisited = true;

            System.out.print("Printing path for node " + currentNode.name + ": ");
            printPath(currentNode);
            System.out.println();

            for (Node neighbor : currentNode.neighbors) {
                if (!neighbor.isVisited) {
                    queue.add(neighbor);
                    neighbor.isVisited = true;
                    neighbor.parent = currentNode;
                }
            }
        }
    }

    public void printPath(Node node) {
        if (node.parent != null) {
            printPath(node.parent);
        }
        System.out.print(node.name + " ");
    }

    public static void main(String[] args) {
        ArrayList<Node> nodeList = new ArrayList<>();

        nodeList.add(new Node("A", 0));
        nodeList.add(new Node("B", 1));
        nodeList.add(new Node("C", 2));
        nodeList.add(new Node("D", 3));
        nodeList.add(new Node("E", 4));
        nodeList.add(new Node("F", 5));
        nodeList.add(new Node("G", 6));

        BFS graph = new BFS(nodeList);

        graph.addUndirectedEdge(0, 1);
        graph.addUndirectedEdge(0, 2);
        graph.addUndirectedEdge(1, 3);
        graph.addUndirectedEdge(1, 6);
        graph.addUndirectedEdge(2, 3);
        graph.addUndirectedEdge(2, 4);
        graph.addUndirectedEdge(3, 5);
        graph.addUndirectedEdge(4, 5);
        graph.addUndirectedEdge(5, 6);

        graph.bfsMethod(nodeList.get(0));
    }
}

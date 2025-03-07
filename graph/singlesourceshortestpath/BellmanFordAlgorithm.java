package graph.singlesourceshortestpath;

import java.util.*;

class Node {
    public String name;
    public int index;
    public ArrayList<Node> neighbors;
    public boolean isVisited;
    public Node parent;
    public HashMap<Node, Integer> weightMap;
    public int distance;

    public Node(String name, int index) {
        this.name = name;
        this.index = index;
        this.neighbors = new ArrayList<>();
        this.isVisited = false;
        this.parent = null;
        this.weightMap = new HashMap<>();
        this.distance = Integer.MAX_VALUE;
    }

    @Override
    public String toString() {
        return name;
    }
}

public class BellmanFordAlgorithm {
    private ArrayList<Node> nodeList;

    public BellmanFordAlgorithm(ArrayList<Node> nodeList) {
        this.nodeList = nodeList;
    }

    public void addWeightedDirectedEdge(int i, int j, int weight) {
        Node first = nodeList.get(i);
        Node second = nodeList.get(j);
        first.neighbors.add(second);
        first.weightMap.put(second, weight);
    }

    public void bellmanFord(Node node) {
        node.distance = 0;

        // runs v-1 times
        for (int i = 0; i < nodeList.size() - 1; i++) {
            for (Node currentNode : nodeList) {
                for (Node neighbor : currentNode.neighbors) {
                    if (neighbor.distance > currentNode.distance + currentNode.weightMap.get(neighbor)) {
                        neighbor.distance = (currentNode.distance + currentNode.weightMap.get(neighbor));
                        neighbor.parent = currentNode;
                    }
                }
            }
        }

        System.out.println("Checking for negative cycle...");

        for (Node currentNode : nodeList) {
            for (Node neighbor : currentNode.neighbors) {
                if (neighbor.distance > currentNode.distance + currentNode.weightMap.get(neighbor)) {
                    System.out.println("Negative cycle found");
                    System.out.println("Vertex name: " + neighbor.name);
                    System.out.println("Old cost: " + neighbor.distance);
                    System.out.println("New cost: " + currentNode.distance + currentNode.weightMap.get(neighbor));
                    return;
                }
            }
        }

        System.out.println("Negative cycle not found");

        for (Node nodeToCheck : nodeList) {
            System.out.print("Node " + nodeToCheck + ", distance: " + nodeToCheck.distance + ", Path: ");
            printPath(nodeToCheck);
            System.out.println();
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

        BellmanFordAlgorithm graph = new BellmanFordAlgorithm(nodeList);

        graph.addWeightedDirectedEdge(0, 1, 2);
        graph.addWeightedDirectedEdge(0, 2, 5);
        graph.addWeightedDirectedEdge(1, 2, 6);
        graph.addWeightedDirectedEdge(1, 3, 2);
        graph.addWeightedDirectedEdge(1, 4, 3);
        graph.addWeightedDirectedEdge(2, 5, 8);
        graph.addWeightedDirectedEdge(3, 4, 4);
        graph.addWeightedDirectedEdge(4, 6, 9);
        graph.addWeightedDirectedEdge(5, 6, 7);

        graph.bellmanFord(nodeList.get(0));
    }
}

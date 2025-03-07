package graph.singlesourceshortestpath;

import java.util.*;

class Node implements Comparable<Node> {
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

    @Override
    public int compareTo(Node o) {
        return this.distance - o.distance;
    }
}
public class DijkstraAlgorithm {
    private ArrayList<Node> nodeList;

    public DijkstraAlgorithm(ArrayList<Node> nodeList) {
        this.nodeList = nodeList;
    }

    public void addWeightedDirectedEdge(int i, int j, int weight) {
        Node first = nodeList.get(i);
        Node second = nodeList.get(j);
        first.neighbors.add(second);
        first.weightMap.put(second, weight);
    }

    public void dijkstra(Node node) {
        PriorityQueue<Node> queue = new PriorityQueue<>();
        node.distance = 0;
        queue.addAll(nodeList);

        while(!queue.isEmpty()) {
            Node currentNode = queue.remove();
            for (Node neighbor : currentNode.neighbors) {
                if (queue.contains(neighbor)) {
                    if (neighbor.distance > currentNode.distance + currentNode.weightMap.get(neighbor)) {
                        neighbor.distance = (currentNode.distance + currentNode.weightMap.get(neighbor));
                        neighbor.parent = currentNode;
                        // refresh queue
                        queue.remove(neighbor);
                        queue.add(neighbor);
                    }
                }
            }
        }

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

        DijkstraAlgorithm graph = new DijkstraAlgorithm(nodeList);

        graph.addWeightedDirectedEdge(0, 1, 2);
        graph.addWeightedDirectedEdge(0, 2, 5);
        graph.addWeightedDirectedEdge(1, 2, 6);
        graph.addWeightedDirectedEdge(1, 3, 2);
        graph.addWeightedDirectedEdge(1, 4, 3);
        graph.addWeightedDirectedEdge(2, 5, 8);
        graph.addWeightedDirectedEdge(3, 4, 4);
        graph.addWeightedDirectedEdge(4, 6, 9);
        graph.addWeightedDirectedEdge(5, 6, 7);

        graph.dijkstra(nodeList.get(0));
    }
}

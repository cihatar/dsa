package graph.allpairsshortestpath;

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

public class FloydWarshallAlgorithm {
    private ArrayList<Node> nodeList;

    public FloydWarshallAlgorithm(ArrayList<Node> nodeList) {
        this.nodeList = nodeList;
    }

    public void addWeightedDirectedEdge(int i, int j, int weight) {
        Node first = nodeList.get(i);
        Node second = nodeList.get(j);
        first.neighbors.add(second);
        first.weightMap.put(second, weight);
    }

    public void floydWarshall() {
        int size = nodeList.size();
        int[][] V = new int[size][size];

        for (int i = 0; i < size; i++) {
            Node first = nodeList.get(i);
            for (int j = 0; j < size; j++) {
                Node second = nodeList.get(j);
                // distance between itself
                if (i == j) {
                    V[i][j] = 0;
                }
                // direct link between first and second
                else if (first.weightMap.containsKey(second)) {
                    V[i][j] = first.weightMap.get(second);
                }
                // there is a direct link
                else {
                    V[i][j] = Integer.MAX_VALUE / 10;
                }
            }
        }

        for (int k = 0; k < size; k++) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (V[i][j] > V[i][k] + V[k][j]) {
                        V[i][j] = V[i][k] + V[k][j];
                    }
                }
            }
        }

        for (int i = 0; i < size; i++) {
            System.out.print("Distance for node " + nodeList.get(i) + ": ");
            for (int j = 0; j < size; j++) {
                System.out.print(V[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        ArrayList<Node> nodeList = new ArrayList<>();

        nodeList.add(new Node("A", 0));
        nodeList.add(new Node("B", 1));
        nodeList.add(new Node("C", 2));
        nodeList.add(new Node("D", 3));

        FloydWarshallAlgorithm graph = new FloydWarshallAlgorithm(nodeList);

        graph.addWeightedDirectedEdge(0, 1, 8);
        graph.addWeightedDirectedEdge(0, 3, 1);
        graph.addWeightedDirectedEdge(1, 2, 1);
        graph.addWeightedDirectedEdge(2, 0, 4);
        graph.addWeightedDirectedEdge(3, 1, 2);
        graph.addWeightedDirectedEdge(3, 2, 9);

        graph.floydWarshall();
    }
}

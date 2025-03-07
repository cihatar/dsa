package graph.kruskalandprim;

import java.util.*;

class Node implements Comparable<Node> {
    public String name;
    public ArrayList<Node> neighbors;
    public boolean isVisited;
    public Node parent;
    public HashMap<Node, Integer> weightMap;
    public int distance;
    public DisjointSet set;

    public Node(String name) {
        this.name = name;
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

public class DisjointSet {
    private ArrayList<Node> nodeList = new ArrayList<>();

    public static void makeSet(ArrayList<Node> nodeList) {
        for (Node node : nodeList) {
            DisjointSet set = new DisjointSet();
            set.nodeList.add(node);
            node.set = set;
        }
    }

    public static DisjointSet findSet(Node node) {
        return node.set;
    }

    public static DisjointSet union(Node node1, Node node2) {
        if (node1.set.equals(node2.set)) {
            return null;
        }
        DisjointSet set1 = node1.set;
        DisjointSet set2 = node2.set;    

        // merge
        if (set1.nodeList.size() > set2.nodeList.size()) {
            for (Node node : set2.nodeList) {
                node.set = set1;
                set1.nodeList.add(node);
            }
            return set1;
        }   
        else {
            for (Node node : set1.nodeList) {
                node.set = set2;
                set2.nodeList.add(node);
            }
            return set2;
        }
    }

    public void print() {
        for (Node node : nodeList) {
            System.out.print(node + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        ArrayList<Node> nodeList = new ArrayList<>();

        nodeList.add(new Node("A"));
        nodeList.add(new Node("B"));
        nodeList.add(new Node("C"));
        nodeList.add(new Node("D"));

        DisjointSet.makeSet(nodeList);

        Node firstNode = nodeList.get(0);
        Node secondNode = nodeList.get(1);

        DisjointSet output = DisjointSet.findSet(secondNode);
        output.print();

        DisjointSet.union(firstNode, secondNode);

        output.print();
    }
}

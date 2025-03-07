package graph.kruskalandprim;

import java.util.*;

class UndirectedEdge {
    public Node first;
    public Node second;
    public int weight;

    public UndirectedEdge(Node first, Node second, int weight) {
        this.first = first;
        this.second = second;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Edge (" + first + ", " + second + "), weight = " + weight;
    }
}

public class KruskalAlgorithm {
    private ArrayList<Node> nodeList;
    private ArrayList<UndirectedEdge> edgeList;

    public KruskalAlgorithm(ArrayList<Node> nodeList) {
        this.nodeList = nodeList;
        this.edgeList = new ArrayList<>();
    }

    public void addWeightedUndirectedEdge(int firstIndex, int secondIndex, int weight) {
        UndirectedEdge edge = new UndirectedEdge(nodeList.get(firstIndex), nodeList.get(secondIndex), weight);
        Node first = edge.first;
        Node second = edge.second;
        first.neighbors.add(second);
        second.neighbors.add(first);
        first.weightMap.put(second, weight);
        second.weightMap.put(first, weight);
        edgeList.add(edge);
    }

    public void kruskal() {
        DisjointSet.makeSet(nodeList);

        Comparator<UndirectedEdge> comparator = new Comparator<UndirectedEdge>() {
            @Override 
            public int compare(UndirectedEdge o1, UndirectedEdge o2) {
                return o1.weight - o2.weight;
            }
        };

        Collections.sort(edgeList, comparator);

        int cost = 0;
        for (UndirectedEdge edge : edgeList) {
            Node first = edge.first;
            Node second = edge.second;
            if (!DisjointSet.findSet(first).equals(DisjointSet.findSet(second))) {
                DisjointSet.union(first, second);
                cost += edge.weight;
                System.out.println("Taken " + edge);
            }
        }

        System.out.println("Total cost of MST: " + cost);
    }

    public static void main(String[] args) {
        ArrayList<Node> nodeList = new ArrayList<>();

        nodeList.add(new Node("A"));
        nodeList.add(new Node("B"));
        nodeList.add(new Node("C"));
        nodeList.add(new Node("D"));
        nodeList.add(new Node("E"));

        KruskalAlgorithm graph = new KruskalAlgorithm(nodeList);

        graph.addWeightedUndirectedEdge(0, 1, 5);
        graph.addWeightedUndirectedEdge(0, 2, 13);
        graph.addWeightedUndirectedEdge(0, 4, 15);
        graph.addWeightedUndirectedEdge(1, 2, 10);
        graph.addWeightedUndirectedEdge(1, 3, 8);
        graph.addWeightedUndirectedEdge(2, 3, 6);
        graph.addWeightedUndirectedEdge(2, 4, 20);

        graph.kruskal();
    }
}

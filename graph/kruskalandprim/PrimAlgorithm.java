package graph.kruskalandprim;

import java.util.*;

public class PrimAlgorithm {
     private ArrayList<Node> nodeList;

    public PrimAlgorithm(ArrayList<Node> nodeList) {
        this.nodeList = nodeList;
    }

    public void addWeightedUndirectedEdge(int i, int j, int weight) {
        Node first = nodeList.get(i);
        Node second = nodeList.get(j);
        first.neighbors.add(second);
        second.neighbors.add(first);
        first.weightMap.put(second, weight);
        second.weightMap.put(first, weight);
    }

    public void prim(Node node) {
        for (int i = 0; i < nodeList.size(); i++) {
            nodeList.get(i).distance = Integer.MAX_VALUE;
        }

        PriorityQueue<Node> queue = new PriorityQueue<>();
        node.distance = 0;
        queue.addAll(nodeList);

        while(!queue.isEmpty()) {
            Node currentNode = queue.remove();
            for (Node neighbor : currentNode.neighbors) {
                if (queue.contains(neighbor)) {
                    if (neighbor.distance > currentNode.weightMap.get(neighbor)) {
                        neighbor.distance = currentNode.weightMap.get(neighbor);
                        neighbor.parent = currentNode;
                        // refresh queue
                        queue.remove(neighbor);
                        queue.add(neighbor);
                    }
                }
            }
        }

        int cost = 0;
        for (Node nodeToCheck : nodeList) {
            System.out.println("Node " + nodeToCheck + ", key " + nodeToCheck.distance + " Parent: " + nodeToCheck.parent);
            cost += nodeToCheck.distance;
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

        PrimAlgorithm graph = new PrimAlgorithm(nodeList);

        graph.addWeightedUndirectedEdge(0, 1, 5);
        graph.addWeightedUndirectedEdge(0, 2, 13);
        graph.addWeightedUndirectedEdge(0, 4, 15);
        graph.addWeightedUndirectedEdge(1, 2, 10);
        graph.addWeightedUndirectedEdge(1, 3, 8);
        graph.addWeightedUndirectedEdge(2, 3, 6);
        graph.addWeightedUndirectedEdge(2, 4, 20);

        graph.prim(nodeList.get(0));
    }
}

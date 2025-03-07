package graph;

import java.util.*;

class Node {
    public String name;
    public int index;
    public boolean isVisited;

    public Node(String name, int index) {
        this.name = name;
        this.index = index;
        this.isVisited = false;
    }
}

public class GraphWithAdjacencyMatrix {
    private ArrayList<Node> nodeList;
    private int[][] adjacencyMatrix;

    public GraphWithAdjacencyMatrix(ArrayList<Node> nodeList) {
        this.nodeList = nodeList;
        this.adjacencyMatrix = new int[nodeList.size()][nodeList.size()];
    }

    public void addUndirectedEdge(int i, int j) {
        adjacencyMatrix[i][j] = 1;
        adjacencyMatrix[j][i] = 1;
    }

    public void addDirectedEdge(int i, int j) {
        adjacencyMatrix[i][j] = 1;
    }

    private ArrayList<Node> getNeighbors(Node node) {
        ArrayList<Node> neighbors = new ArrayList<>();
        int nodeIndex = node.index;
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            if (adjacencyMatrix[nodeIndex][i] == 1) {
                neighbors.add(nodeList.get(i));
            }
        }
        return neighbors;
    }

    private void bfsVisit(Node node) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        while (!queue.isEmpty()) {
            Node currentNode = queue.remove();
            currentNode.isVisited = true;
            System.out.print(currentNode.name + " ");

            ArrayList<Node> neighbors = getNeighbors(currentNode);
            for (Node neighbor : neighbors) {
                if (!neighbor.isVisited) {
                    queue.add(neighbor);
                    neighbor.isVisited = true;
                }
            }
        }
    }

    private void dfsVisit(Node node) {
        Stack<Node> stack = new Stack<>();
        stack.push(node);
        while (!stack.isEmpty()) {
            Node currentNode = stack.pop();
            currentNode.isVisited = true;
            System.out.print(currentNode.name + " ");

            ArrayList<Node> neighbors = getNeighbors(currentNode);
            for (Node neighbor : neighbors) {
                if (!neighbor.isVisited) {
                    stack.push(neighbor);
                    neighbor.isVisited = true;
                }
            }
        }
    }

    private void topologicalVisit(Node node, Stack<Node> stack) {
        ArrayList<Node> neighbors = getNeighbors(node);
        for (Node neighbor : neighbors) {
            if (!neighbor.isVisited) {
                topologicalVisit(neighbor, stack);
            }
        }
        node.isVisited = true;
        stack.push(node);
    }

    public void bfs() {
        for (Node node : nodeList) {
            if (!node.isVisited) {
                bfsVisit(node);
            }
        }
    }

    public void dfs() {
        for (Node node : nodeList) {
            if (!node.isVisited) {
                dfsVisit(node);
            }
        }
    }

    public void topologicalSort() {
        Stack<Node> stack = new Stack<>();
        for (Node node : nodeList) {
            if (!node.isVisited) {
                topologicalVisit(node, stack);
            }
        }
        while (!stack.isEmpty()) {
            Node currentNode = stack.pop();
            System.out.println(currentNode.name + " ");
        }
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("   ");
        for (int i = 0; i < nodeList.size(); i++) {
            s.append(nodeList.get(i).name + " ");
        }
        s.append("\n");
        for (int i = 0; i < nodeList.size(); i++) {
            s.append(nodeList.get(i).name + ": ");
            for (int j : adjacencyMatrix[i]) {
                s.append((j) + " ");
            }
            s.append("\n");
        }
        return s.toString();
    }

    public static void main(String[] args) {
        ArrayList<Node> nodeList = new ArrayList<>();

        nodeList.add(new Node("A", 0));
        nodeList.add(new Node("B", 1));
        nodeList.add(new Node("C", 2));
        nodeList.add(new Node("D", 3));
        nodeList.add(new Node("E", 4));
        nodeList.add(new Node("F", 5));

        GraphWithAdjacencyMatrix graph = new GraphWithAdjacencyMatrix(nodeList);

        graph.addDirectedEdge(0, 1);
        graph.addDirectedEdge(0, 2);
        graph.addDirectedEdge(0, 3);
        graph.addDirectedEdge(1, 4);
        graph.addDirectedEdge(2, 3);
        graph.addDirectedEdge(3, 4);
        graph.addDirectedEdge(4, 5);

        System.out.println(graph);

        graph.topologicalSort();
    }
}

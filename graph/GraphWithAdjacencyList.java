package graph;

import java.util.*;

class Node {
    public String name;
    public int index;
    public ArrayList<Node> neighbors;
    public boolean isVisited;

    public Node(String name, int index) {
        this.name = name;
        this.index = index;
        this.neighbors = new ArrayList<>();
        this.isVisited = false;
    }
}

public class GraphWithAdjacencyList {
    private ArrayList<Node> nodeList;

    public GraphWithAdjacencyList(ArrayList<Node> nodeList) {
        this.nodeList = nodeList;
    }

    public void addUndirectedEdge(int i, int j) {
        Node first = nodeList.get(i);
        Node second = nodeList.get(j);
        first.neighbors.add(second);
        second.neighbors.add(first);
    }

    public void addDirectedEdge(int i, int j) {
        Node first = nodeList.get(i);
        Node second = nodeList.get(j);
        first.neighbors.add(second);
    }

    private void bfsVisit(Node node) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        while (!queue.isEmpty()) {
            Node currentNode = queue.remove();
            currentNode.isVisited = true;
            System.out.print(currentNode.name + " ");

            for (Node neighbor : currentNode.neighbors) {
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

            for (Node neighbor : currentNode.neighbors) {
                if (!neighbor.isVisited) {
                    stack.push(neighbor);
                    neighbor.isVisited = true;
                }
            }
        }
    }

    private void topologicalVisit(Node node, Stack<Node> stack) {
        for (Node neigbor : node.neighbors) {
            if (!neigbor.isVisited) {
                topologicalVisit(neigbor, stack);
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
        for (int i = 0; i < nodeList.size(); i++) {
            s.append(nodeList.get(i).name + ": ");
            for (int j = 0; j < nodeList.get(i).neighbors.size(); j++) {
                if (j == nodeList.get(i).neighbors.size() - 1) {
                    s.append((nodeList.get(i).neighbors.get(j).name));
                }
                else {
                    s.append((nodeList.get(i).neighbors.get(j).name) + " -> ");
                }
            }
        }
        s.append("\n");
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

        GraphWithAdjacencyList graph = new GraphWithAdjacencyList(nodeList);

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

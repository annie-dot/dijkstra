package graph;

import java.util.*;

public class Graph {
    private final List<Node> nodes = new LinkedList<>();
    private final Map<Node, List<Edge>> adjacencyMap = new HashMap<>();

    public void addNode(Node node) {
        nodes.add(node);
    }

    public void addEdge(Node from, Node to, int weight) {
        Edge edge = new Edge(to, weight);
        adjacencyMap.computeIfAbsent(from, k -> new LinkedList<>()).add(edge);
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public Node getNodeByName(String name) {
        return nodes.stream()
                .filter(node -> node.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    public List<Edge> getAdjacencyList(Node node) {
        return adjacencyMap.computeIfAbsent(node, k -> new LinkedList<>());
    }

    public static class Node {
        private final String name;

        public Node(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return Objects.equals(name, node.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }

        @Override
        public String toString() {
            return "(" + name + ")";
        }
    }

    public static class Edge {
        private final Node destination;
        private final int weight;

        public Edge(Node destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }

        public Node getDestination() {
            return destination;
        }

        public int getWeight() {
            return weight;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Edge edge = (Edge) o;
            return weight == edge.weight &&
                    Objects.equals(destination, edge.destination);
        }

        @Override
        public int hashCode() {
            return Objects.hash(destination, weight);
        }

        @Override
        public String toString() {
            return "--" + weight + "--> (" + destination.getName() + ")";
        }
    }
}

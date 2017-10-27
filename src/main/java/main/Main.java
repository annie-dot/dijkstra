package main;

import graph.Graph;
import graph.Graph.Edge;
import graph.Graph.Node;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph();
        Scanner scanner = new Scanner(System.in);
        int nodesCount = scanner.nextInt();
        int edgesCount = scanner.nextInt();
        List<Node> nodes = new ArrayList<>(nodesCount);
        for (int i = 1; i <= nodesCount; ++i) {
            Node node = new Node(Integer.toString(i));
            nodes.add(node);
            graph.addNode(node);
        }
        for (int i = 0; i < edgesCount; ++i) {
            int from = scanner.nextInt() - 1;
            int to = scanner.nextInt() - 1;
            int weight = scanner.nextInt();
            graph.addEdge(nodes.get(from), nodes.get(to), weight);
        }
        int source = scanner.nextInt() - 1;
        int destination = scanner.nextInt() - 1;

        System.out.println(Dijkstra.shortestPath(graph, nodes.get(source), nodes.get(destination)));
    }

    static class Dijkstra {
        public static final int INF = 999999;

        public static Integer shortestPath(Graph graph, Node source, Node destination) {
            Map<Node, Integer> distances = graph.getNodes().stream()
                    .collect(Collectors.toMap(node -> node, node -> INF));
            distances.put(source, 0);
            Map<Node, Integer> unvisitedNodes = new HashMap<>();
            unvisitedNodes.putAll(distances);
            while (!unvisitedNodes.isEmpty()) {
                Node v = getNodeWithMinimalWeight(unvisitedNodes);
                unvisitedNodes.remove(v);
                for (Edge edge : graph.getAdjacencyList(v)) {
                    Node u = edge.getDestination();
                    if (unvisitedNodes.containsKey(u)) {
                        if (distances.get(u) > distances.get(v) + edge.getWeight()) {
                            distances.put(u, distances.get(v) + edge.getWeight());
                            unvisitedNodes.put(u, distances.get(v) + edge.getWeight());
                        }
                    }
                }
            }
            Integer distance = distances.get(destination);
            if (distance == null || distance == Dijkstra.INF) {
                distance = -1;
            }
            return distance;
        }

        private static Node getNodeWithMinimalWeight(Map<Node, Integer> unvisitedNodes) {
            return unvisitedNodes.entrySet().stream()
                    .sorted(Map.Entry.comparingByValue())
                    .findFirst()
                    .orElse(null)
                    .getKey();
        }
    }
}

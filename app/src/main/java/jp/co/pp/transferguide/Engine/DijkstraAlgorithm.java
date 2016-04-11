package jp.co.pp.transferguide.Engine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by softwise024 on 2016/04/11.
 */
public class DijkstraAlgorithm {


    private final List<Graph.Node> nodes;
    private final List<Graph.Edge> edges;
    private Set<Graph.Node> settledNodes;
    private Set<Graph.Node> unSettledNodes;
    private Map<Graph.Node, Graph.Node> predecessors;
    private Map<Graph.Node, Integer> distance;

    public DijkstraAlgorithm(Graph graph) {
        // create a copy of the array so that we can operate on this array
        this.nodes = new ArrayList<Graph.Node>(graph.nodeList);
        this.edges = new ArrayList<Graph.Edge>(graph.edgeList);
    }

    public void execute(Graph.Node source) {
        settledNodes = new HashSet<Graph.Node>();
        unSettledNodes = new HashSet<Graph.Node>();
        distance = new HashMap<Graph.Node, Integer>();
        predecessors = new HashMap<Graph.Node, Graph.Node>();
        distance.put(source, 0);
        unSettledNodes.add(source);
        while (unSettledNodes.size() > 0) {
            Graph.Node node = getMinimum(unSettledNodes);
            settledNodes.add(node);
            unSettledNodes.remove(node);
            findMinimalDistances(node);
        }
    }

    private void findMinimalDistances(Graph.Node node) {
        List<Graph.Node> adjacentNodes = getNeighbors(node);
        for (Graph.Node target : adjacentNodes) {
            if (getShortestDistance(target) > getShortestDistance(node)
                    + getDistance(node, target)) {
                distance.put(target, getShortestDistance(node)
                        + getDistance(node, target));
                predecessors.put(target, node);
                unSettledNodes.add(target);
            }
        }

    }

    private int getDistance(Graph.Node node, Graph.Node target) {
        for (Graph.Edge edge : edges) {
            if (edge.from.equals(node)
                    && edge.to.equals(target)) {
                return edge.weight;
            }
        }
        throw new RuntimeException("Should not happen");
    }

    private List<Graph.Node> getNeighbors(Graph.Node node) {
        List<Graph.Node> neighbors = new ArrayList<Graph.Node>();
        for (Graph.Edge edge : edges) {
            if (edge.from.equals(node)
                    && !isSettled(edge.to)) {
                neighbors.add(edge.to);
            }
        }
        return neighbors;
    }

    private Graph.Node getMinimum(Set<Graph.Node> Nodees) {
        Graph.Node minimum = null;
        for (Graph.Node Node : Nodees) {
            if (minimum == null) {
                minimum = Node;
            } else {
                if (getShortestDistance(Node) < getShortestDistance(minimum)) {
                    minimum = Node;
                }
            }
        }
        return minimum;
    }

    private boolean isSettled(Graph.Node Node) {
        return settledNodes.contains(Node);
    }

    private int getShortestDistance(Graph.Node destination) {
        Integer d = distance.get(destination);
        if (d == null) {
            return Integer.MAX_VALUE;
        } else {
            return d;
        }
    }

    /*
     * This method returns the path from the source to the selected target and
     * NULL if no path exists
     */
    public LinkedList<Graph.Node> getPath(Graph.Node target) {
        LinkedList<Graph.Node> path = new LinkedList<Graph.Node>();
        Graph.Node step = target;
        // check if a path exists
        if (predecessors.get(step) == null) {
            return null;
        }
        path.add(step);
        while (predecessors.get(step) != null) {
            step = predecessors.get(step);
            path.add(step);
        }
        // Put it into the correct order
        Collections.reverse(path);
        return path;
    }
}

package labs.lab12.il.ac.telhai.ds.graph;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public class Graph<V extends Comparable<V>, E> implements IGraph<V, E> {

    private final Map<V, List<Edge>> map;

    public Graph() {
        map = new TreeMap<>();
    }

    public TreeMap<V, Double> distancesFrom(V s) {
        TreeMap<V, Double> minDesMap = new TreeMap<>();
        HashMap<V, Node> nodesByV = new HashMap<>();
        PriorityQueue<Node> queue = new PriorityQueue<>();

        for (V v : map.keySet()) {
            Node node = new Node(v, Double.MAX_VALUE);
            if (v.equals(s)) {
                node.setDistance(0);
            }
            nodesByV.put(v, node);
            queue.add(node);
        }

        while (!queue.isEmpty()) {
            Node min = queue.poll();
            if (min.getDistance() == Double.MAX_VALUE) {
                return minDesMap;
            }
            nodesByV.remove(min.getV());

            V v = min.getV();
            minDesMap.put(v, min.getDistance());
            var edges = map.get(v);
            for (Edge edge : edges) {
                V u = edge.getOtherVertex(v);
                if (minDesMap.containsKey(edge.getVertex1()) && minDesMap.containsKey(edge.getVertex2())) {
                    continue;
                }
                Node node = nodesByV.get(u);
                node.setDistance(Math.min(node.getDistance(), min.getDistance() + getWeight(v, u)));
                queue.remove(node);
                queue.add(node);
            }
        }
        return minDesMap;
    }


    @Override
    public void add(V v) {
        if (map.containsKey(v)) {
            return;
        }

        map.put(v, new LinkedList<>());
    }

    @Override
    public E getEdge(V u, V v) {
        var res = map.get(u).stream().filter(e -> e.containsVertices(v)).findFirst();
        AtomicReference<E> edgeLable = new AtomicReference<>(null);
        res.ifPresent(edge -> {
            map.get(u).remove(edge);
            map.get(v).remove(edge);
            edgeLable.set(edge.label);
        });

        return edgeLable.get();
    }

    @Override
    public E putEdge(V u, V v, E edgeLabel) {
        add(u);
        add(v);

        removeEdge(u, v);

        var edge = new Edge(u, v, edgeLabel);
        map.get(u).add(edge);
        map.get(v).add(edge);

        return edgeLabel;
    }

    @Override
    public boolean containsVertex(V v) {
        return map.containsKey(v);
    }

    @Override
    public void removeVertex(V v) {
        map.remove(v);

        map.values().forEach(edges -> edges.removeIf(e -> e.containsVertices(v)));
    }

    @Override
    public E removeEdge(V u, V v) {
        var res = map.get(u).stream().filter(e -> e.containsVertices(v)).findFirst();

        res.ifPresent(edge -> {
            map.get(u).remove(edge);
            map.get(v).remove(edge);
        });
        return null;
    }

    @Override
    public double getWeight(V u, V v) {
        var res = map.get(u).stream().filter(l -> l.containsVertices(v)).findFirst();
        var edge = res.orElse(null);

        if (edge == null) {
            return 0;
        }

        if (edge.getLabel() instanceof Weighted) {
            return ((Weighted) edge.getLabel()).getWeight();
        }

        if (edge.getLabel() instanceof Number) {
            return (double) edge.getLabel();
        }
        throw new IllegalArgumentException("Edge is not weighted");
    }

    @Override
    public String toStringExtended() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<V, List<Edge>> entry : map.entrySet()) {
            sb.append(entry.getKey());

            sb.append(":");
            for (Edge edge : entry.getValue()) {
                sb.append("{");
                sb.append(edge.getVertex1());
                sb.append(",");
                sb.append(edge.getVertex2());
                sb.append("}");
                if (edge.getLabel() instanceof Weighted || edge.getLabel() instanceof Number) {
                    sb.append("(");
                    sb.append(edge.getLabel());
                    sb.append("),");
                }
            }
            if (sb.charAt(sb.length() - 1) == ',') {
                sb.deleteCharAt(sb.length() - 1);
            }
            sb.append("\n");
        }

        if (!map.isEmpty()) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (V s : map.keySet()) {
            sb.append(s).append(',');
        }

        if (!map.isEmpty()) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    @Override
    public boolean areAdjacent(V u, V v) {
        if (!containsVertex(u)) {
            return false;
        }
        return map.get(u).stream().anyMatch(edge -> edge.containsVertices(v));
    }

    private class Node implements Comparable<Node> {
        private final V v;
        private double distance;

        public Node(V v, double distance) {
            this.v = v;
            this.distance = distance;
        }

        public V getV() {
            return v;
        }

        public double getDistance() {
            return distance;
        }

        public void setDistance(double distance) {
            this.distance = distance;
        }

        @Override
        public int compareTo(Node o) {
            return Double.compare(distance, o.distance);
        }
    }

    private class Edge {
        private final V vertex1;
        private final V vertex2;
        private final E label;

        public Edge(V vertex1, V vertex2, E label) {
            this.vertex1 = vertex1;
            this.vertex2 = vertex2;
            this.label = label;
        }

        public E getLabel() {
            return label;
        }

        public V getVertex1() {
            return vertex1;
        }

        public V getVertex2() {
            return vertex2;
        }

        public boolean containsVertices(V v) {
            return vertex1.equals(v) || vertex2.equals(v);
        }

        public V getOtherVertex(V v) {
            return vertex1.equals(v) ? vertex2 : vertex1;
        }
    }
}

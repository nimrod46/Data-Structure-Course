package labs.lab11.il.ac.telhai.ds.graph;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicReference;

public class Graph<V extends Comparable<V>, E> implements IGraph<V, E> {

    private final Map<V, List<Edge>> map;

    public Graph() {
        map = new TreeMap<>();
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
                if(edge.getLabel() instanceof Weighted || edge.getLabel() instanceof Number) {
                    sb.append("(");
                    sb.append(edge.getLabel());
                    sb.append("),");
                }
            }
            if(sb.charAt(sb.length() - 1) == ',') {
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
    }
}

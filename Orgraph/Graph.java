import java.util.*;

public class Graph<T> {
    private List<Vertex<T>> vertices = new ArrayList<>();

    public Vertex<T> createVertex(T value) {
        Vertex<T> v = new Vertex<>(value);
        vertices.add(v);
        return v;
    }

    public void createEdge(Vertex<T> from, Vertex<T> to) {
        // добавляем в список смежности только в одном направлении
        from.getAdjacent().add(to);
    }

    public int path(Vertex<T> from, Vertex<T> to) {
            Map<Vertex<T>, Integer> paths = new HashMap<>();
            paths.put(from, 0);

            Queue<Vertex<T>> queue = new ArrayDeque<>();
            Set<Vertex<T>> added = new HashSet<>();
            queue.add(from);
            added.add(from);

            while (!queue.isEmpty()) {
                Vertex<T> v = queue.poll(); // вынимаем следующий элемент из очереди

                if (v.equals(to)) { // если нашли целевую вершину
                    return paths.get(v); // возвращаем длину пути
                }

                for (Vertex<T> neighbor : v.getAdjacent()) {
                    if (!added.contains(neighbor)) {
                        paths.put(neighbor, paths.get(v) + 1); // путь на 1 больше, чем у текущей вершины
                        added.add(neighbor);
                        queue.add(neighbor);
                    }
                }
            }

        return -1;
    }

}
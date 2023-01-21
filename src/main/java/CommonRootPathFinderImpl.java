import java.util.*;

public class CommonRootPathFinderImpl<T> implements PathService<T> {

    public String findPath(Node<T> n1, Node<T> n2) {
        final Path<T> path1 = pathToRoot(n1);
        final Path<T> path2 = pathToRoot(n2);
        final List<Node<T>> combinedPath = new ArrayList<>();

        int index1 = 0;
        int index2 = 0;

        Collections.reverse(path2.path());

        while (index1 < path1.path().size() && index2 < path2.path().size()) {
            final var current1 = path1.path().get(index1);
            final var current2 = path2.path().get(index2);

            if (current1.equals(current2)) {
                for (int i = 0; i <= index1; i++)
                    combinedPath.add(path1.path().get(i));

                for (int i = index2 + 1; i < path2.path().size(); i++)
                    combinedPath.add(path2.path().get(i));

                break;
            }

            if (!path2.nodes().contains(current1))
                index1++;

            if (!path1.nodes().contains(current2))
                index2++;
        }

        return Utils.nodeListToString(combinedPath);
    }

    private Path<T> pathToRoot(Node<T> n) {
        final List<Node<T>> path = new ArrayList<>();
        final Set<Node<T>> pathNodes = new HashSet<>();
        var current = n;

        while (current != null) {
            path.add(current);
            pathNodes.add(current);
            current = current.parent();
        }

        return new Path<>(path, pathNodes);
    }

}

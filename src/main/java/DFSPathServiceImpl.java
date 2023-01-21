import java.util.*;

public class DFSPathServiceImpl<T> implements PathService<T> {

    public String findPath(Node<T> n1, Node<T> n2) {
        final List<Node<T>> path = recursiveFindPath(n1, n2, new HashSet<>());

        Collections.reverse(path);

        return Utils.nodeListToString(path);
    }

    private List<Node<T>> recursiveFindPath(
            Node<T> n,
            Node<T> search,
            Set<Node<T>> visitedNodes
    ) {

        if (n.equals(search)) {
            final List<Node<T>> result = new ArrayList<>();
            result.add(n);

            return result;
        }

        if (visitedNodes.contains(n))
            return Collections.emptyList();

        visitedNodes.add(n);

        if (n.children() != null && !n.children().isEmpty()) {
            for (final Node<T> child : n.children()) {
                final List<Node<T>> result = recursiveFindPath(child, search, visitedNodes);

                if (!result.isEmpty()) {
                    result.add(n);
                    return result;
                }
            }
        }

        if (n.parent() != null) {
            final List<Node<T>> result = recursiveFindPath(n.parent(), search, visitedNodes);

            if (!result.isEmpty()) {
                result.add(n);
                return result;
            }
        }

        return Collections.emptyList();
    }

}

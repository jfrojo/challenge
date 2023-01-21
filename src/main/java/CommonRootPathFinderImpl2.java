import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CommonRootPathFinderImpl2<T> implements PathService<T> {

    public String findPath(Node<T> n1, Node<T> n2) {
        final Set<Node<T>> pathNodes = new HashSet<>();
        final List<Node<T>> combinedPath = new ArrayList<>();

        var current1 = n1;
        var current2 = n2;
        var index1 = 0;

        while (current1 != null || current2 != null) {
            if (current1 != null)
                combinedPath.add(index1++, current1);

            if (current2 != null)
                combinedPath.add(index1, current2);

            if (pathNodes.contains(current2) || pathNodes.contains(current1))
                break;

            pathNodes.add(current1);
            pathNodes.add(current2);

            if (current1 !=  null)
                current1 = current1.parent();

            if (current2 !=  null)
                current2 = current2.parent();
        }

        if (current1 == null && current2 == null)
            throw new IllegalArgumentException("There's no path between the nodes");

        return Utils.nodeListToString(combinedPath);
    }

}

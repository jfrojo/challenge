import java.util.List;
import java.util.Set;

public record Path<T> (List<Node<T>> path, Set<Node<T>> nodes) {
}

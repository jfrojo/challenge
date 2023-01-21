import java.util.*;

public record Node<T> (Node<T> parent, Set<Node<T>> children, T value) {

    public Node(Node<T> parent, T value) {
        this(parent, new HashSet<>(), value);
    }

    public boolean addChildren(Collection<? extends Node<T>> children) {
        return this.children.addAll(children);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node<?> node = (Node<?>) o;
        return value == node.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

}
import java.util.List;

public class Utils {

    public static <T> String nodeListToString(List<Node<T>> c) {
        final StringBuilder sb = new StringBuilder("[");

        for (int i = 0; i < c.size(); i++) {
            sb.append(c.get(i).value());

            if (i != c.size() - 1)
                sb.append(",");
        }

        sb.append("]");

        return sb.toString();
    }

}

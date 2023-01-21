import java.time.Duration;
import java.time.Instant;

public class ProfilerService {

    private final int times;

    public ProfilerService(int times) {
        this.times = times;
    }

    public <T> ProfilerResult<?> profile(PathService<T> pathService, Node<T> n1, Node<T> n2) {
        long elapsed = 0L;
        Object result = "";
        final var startTotal = Instant.now();
        for (int i = 0; i < times; i++) {
            final var start = Instant.now();
            result = pathService.findPath(n1, n2);

            elapsed += Duration.between(start, Instant.now()).toMillis();
        }
        final var elapsedTotal = Duration.between(startTotal, Instant.now()).toMillis();

        return new ProfilerResult<>(elapsed, result, elapsedTotal);
    }

}

package full.features.java9.api.stream;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Stream;

public class DropTakeWhile {

    final List<LogMessage> messages;

    public DropTakeWhile(List<LogMessage> messages) {
        this.messages = Objects.requireNonNull(messages);
    }

    public static void main(String[] args) {
        DropTakeWhile messages = new DropTakeWhile(List.of(
                new LogMessage("Starting", Priority.INFO), new LogMessage("Started", Priority.INFO),
                new LogMessage("High Temperature Detected", Priority.WARNING), new LogMessage("Overheated", Priority.ERROR),
                new LogMessage("Shutdown initiated", Priority.INFO)));

        System.out.println("FROM FIRST WARNING:");
        messages.fromFirstWarning().forEach(System.out::println);
        System.out.println();

        System.out.println("UNTIL FIRST ERROR:");
        messages.untilFirstError().forEach(System.out::println);
        System.out.println();

        System.out.println("FROM FIRST WARNING TO FOLLOWING ERROR:");
        messages.fromFirstWarningToFollowingError().forEach(System.out::println);
        System.out.println();
    }

    public Stream<LogMessage> fromFirstWarning() {
        return messages.stream()
                .dropWhile(message -> message.priority.lessThan(Priority.WARNING));
    }

    public Stream<LogMessage> untilBeforeFirstError() {
        return messages.stream()
                .takeWhile(message -> message.priority.atLeast(Priority.ERROR));
    }

    public Stream<LogMessage> untilFirstError() {
        AtomicBoolean sawFirstError = new AtomicBoolean(false);
        return messages.stream()
                .takeWhile(message -> !sawFirstError.getAndSet(message.priority.atLeast(Priority.ERROR)));
    }

    public Stream<LogMessage> fromFirstWarningToFollowingError() {
        AtomicBoolean sawFirstError = new AtomicBoolean(false);
        return messages.stream()
                .dropWhile(message -> message.priority.lessThan(Priority.WARNING))
                .takeWhile(message -> !sawFirstError.getAndSet(message.priority.atLeast(Priority.ERROR)));
    }

    static class LogMessage {

        final String message;
        final Priority priority;

        LogMessage(String message, Priority priority) {
            this.message = Objects.requireNonNull(message);
            this.priority = priority;
        }

        @Override
        public String toString() {
            return "{" + message + ", " + priority + "}";
        }
    }

    enum Priority {
        INFO,
        WARNING,
        ERROR;

        boolean lessThan(Priority other) {
            return other.compareTo(this) > 0;
        }

        boolean atMost(Priority other) {
            return other.compareTo(this) >= 0;
        }

        boolean atLeast(Priority other) {
            return other.compareTo(this) <= 0;
        }

        boolean moreThan(Priority other) {
            return other.compareTo(this) < 0;
        }
    }
}

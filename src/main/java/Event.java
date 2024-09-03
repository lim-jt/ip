import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
        this.type = "E";
    }

    @Override
    public String toFile() {
        return "E|" + getStatusIcon() + "|" + this.description + "|"
                + from.format(DateTimeFormatter.ofPattern("MMM d yyyy h:mm a")) + "|"
                + to.format(DateTimeFormatter.ofPattern("MMM d yyyy h:mm a"));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: "
                + from.format(DateTimeFormatter.ofPattern("MMM d yyyy h:mm a")) + " to: "
                + to.format(DateTimeFormatter.ofPattern("MMM d yyyy h:mm a")) + ")";
    }
}

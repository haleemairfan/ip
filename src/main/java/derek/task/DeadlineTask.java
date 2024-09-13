package derek.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The {@code DeadlineTask} class represents a task with a deadline.
 * It extends the {@code Task} class and includes a deadline date and time associated with the task.
 */
public class DeadlineTask extends Task {
    private String deadline;

    /**
     * Constructs a {@code DeadlineTask} object with the specified name and deadline.
     * The deadline is parsed and formatted to include both date and time in a readable format.
     *
     * @param name the name or description of the task
     * @param deadline the deadline date and time for the task in the format "dd/MM/yyyy HH:mm"
     * @throws DateTimeParseException if the deadline cannot be parsed into the expected format
     */
    public DeadlineTask(String name, String deadline) throws DateTimeParseException {
        super(name);
        this.deadline = this.convertDate(deadline);
    }

    /**
     * Converts the deadline date and time into a specific format.
     * The input format is "dd/MM/yyyy HH:mm", and the output format is "dd MMM yyyy hh:mm a" (12-hour format).
     *
     * @param deadline the deadline string in the input format
     * @return the formatted deadline string
     * @throws DateTimeParseException if the date string cannot be parsed
     */
    public String convertDate(String deadline) throws DateTimeParseException {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime inputDateTime = LocalDateTime.parse(deadline.trim(), inputFormatter);
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a");
        return inputDateTime.format(outputFormatter);
    }

    /**
     * Constructs a {@code DeadlineTask} object with the specified name, deadline, and completion status.
     * If the task is marked as completed, the status is updated accordingly.
     *
     * @param name the name or description of the task
     * @param deadline the deadline date and time for the task
     * @param isCompleted the completion status of the task (e.g., "X" for completed)
     */
    public DeadlineTask(String name, String deadline, String isCompleted) {
        super(name);
        this.deadline = deadline;
        if (isCompleted.equals("X")) {
            super.markCompleted();
        }
    }

    /**
     * Gets the deadline of the task.
     *
     * @return the formatted deadline as a string
     */
    public String getDeadline() {
        return this.deadline;
    }

    /**
     * Gets the name of the task.
     *
     * @return the name of the task
     */
    public String getName() {
        return super.getName();
    }

    /**
     * Returns a string representation of the deadline task, including the task name
     * and deadline date formatted as "(by: date)".
     *
     * @return a formatted string representation of the deadline task
     */
    @Override
    public String toString() {
        return String.format("[D]"
                + super.toString()
                + " (by: "
                + this.deadline
                + ")");
    }
}

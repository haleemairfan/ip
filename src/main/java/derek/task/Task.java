package derek.task;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * The {@code Task} class represents a general task with a name and a completion status.
 * It provides methods to mark the task as completed or incomplete, and factory methods
 * to create specific types of tasks such as {@code DeadlineTask}, {@code EventTask}, and {@code ToDoTask}.
 */
public class Task {
    private String name;
    private Boolean isCompleted;

    private LocalDateTime completionTime;

    /**
     * Constructs a {@code Task} object with the specified name.
     * The task is initially marked as incomplete.
     *
     * @param name the name or description of the task
     */
    public Task(String name) {
        this.name = name;
        this.isCompleted = false;
        this.completionTime = null;
    }

    /**
     * Marks the task as completed.
     */
    public void markCompleted(LocalDateTime completionTime) {
        this.completionTime = completionTime;
        this.isCompleted = true;
    }

    /**
     * Marks the task as incomplete.
     */
    public void markIncomplete() {
        this.isCompleted = false;
        this.completionTime = null;
    }

    /**
     * Returns the completion status of the task.
     *
     * @return "X" if the task is completed, otherwise " "
     */
    public String isCompleted() {
        return this.isCompleted ? "X"
                                : " ";
    }

    /**
     * Creates a new {@code DeadlineTask} with the specified name and deadline.
     *
     * @param name the name or description of the task
     * @param deadline the deadline for the task
     * @return a new {@code DeadlineTask} object
     */
    public static Task deadlineTask(String name, String deadline) {
        return new DeadlineTask(name, deadline);
    }

    /**
     * Creates a new {@code DeadlineTask} with the specified name and deadline.
     *
     * @param name the name or description of the task
     * @param deadline the deadline for the task
     * @return a new {@code DeadlineTask} object
     */
    public static Task deadlineTask(String name, String deadline, String isCompleted,
                                    String completionDate) {
        return new DeadlineTask(name, deadline, isCompleted,
                completionDate);
    }


    /**
     * Creates a new {@code EventTask} with the specified name, start time, and end time.
     *
     * @param name the name or description of the event
     * @param startTime the start time of the event
     * @param endTime the end time of the event
     * @return a new {@code EventTask} object
     */
    public static Task eventTask(String name, String startTime, String endTime) {
        return new EventTask(name, startTime, endTime);
    }

    /**
     * Creates a new {@code EventTask} with the specified name, start time, end time, and completion status.
     *
     * @param name the name or description of the event
     * @param startTime the start time of the event
     * @param endTime the end time of the event
     * @param isCompleted the completion status of the task
     * @return a new {@code EventTask} object
     */
    public static Task eventTask(String name, String startTime,
                                 String endTime, String isCompleted,
                                 String completionDate) {
        return new EventTask(name, startTime, endTime, isCompleted,
                completionDate);
    }


    /**
     * Creates a new {@code ToDoTask} with the specified name.
     *
     * @param name the name or description of the task
     * @return a new {@code ToDoTask} object
     */
    public static Task toDoTask(String name) {
        return new ToDoTask(name);
    }

    /**
     * Creates a new {@code ToDoTask} with the specified name and completion status.
     *
     * @param name the name or description of the task
     * @param isCompleted the completion status of the task
     * @return a new {@code ToDoTask} object
     */
    public static Task toDoTask(String name, String isCompleted,
                                String completionDate) {

        return new ToDoTask(name, isCompleted, completionDate);
    }

    /**
     * Gets the name of the task.
     *
     * @return the name of the task
     */
    public String getName() {
        return this.name;
    }



    /**
     * Returns a string representation of the task, including its completion status and name.
     * The format is "[ ] name" if the task is incomplete, or "[X] name" if the task is completed.
     *
     * @return a formatted string representing the task
     */
    @Override
    public String toString() {
        String completedStatus = "";
        if (!isCompleted) {
            completedStatus += "[ ] ";
        } else {
            completedStatus += "[X] ";
        }
        return String.format(completedStatus + name);
    }


    public LocalDateTime getCompletionTime() {
        return this.completionTime;
    }


}

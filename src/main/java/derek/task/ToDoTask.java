package derek.task;

/**
 * The {@code ToDoTask} class represents a simple task without a specific deadline or time frame.
 * It extends the {@code Task} class and provides a string representation that indicates it is a ToDo task.
 */
public class ToDoTask extends Task {

    /**
     * Constructs a {@code ToDoTask} object with the specified name.
     *
     * @param name the name or description of the ToDo task
     */
    public ToDoTask(String name) {
        super(name);
    }


    /**
     * Constructs a {@code ToDoTask} object with the specified name and completion status.
     *
     * @param name the name or description of the ToDo task
     * @param isCompleted the completion status of the task (e.g., "X" for completed)
     */
    public ToDoTask(String name, String isCompleted) {
        super(name);
        if (isCompleted.equals("X")) {
            super.markCompleted();
        }
    }


    /**
     * Returns a string representation of the ToDo task, including the task name.
     * The format is "[T][ ] name" if the task is incomplete, or "[T][X] name" if the task is completed.
     *
     * @return a formatted string representing the ToDo task
     */
    @Override
    public String toString() {
        return String.format("[T]" + super.toString());
    }

}

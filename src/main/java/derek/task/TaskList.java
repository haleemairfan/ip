package derek.task;

import java.util.ArrayList;

import derek.exception.TaskNotFoundException;



/**
 * The {@code TaskList} class represents a list of tasks. It provides methods to add,
 * remove, retrieve, and display tasks. It also supports populating the task list from
 * a string representation.
 */
public class TaskList {
    private ArrayList<Task> taskList = new ArrayList<>();

    /**
     * Populates the task list based on a string representation of a task.
     * The string should be in the format used for storing tasks.
     *
     * @param task the string representation of the task
     */
    public void populateTaskList(String task) {
        String[] components = task.split("\\|");
        if (components[0].equals("D")) {
            Task newTask = Task.deadlineTask(components[2], components[3],
                    components[1], components[4]);
            taskList.add(newTask);
        } else if (components[0].equals("E")) {
            Task newTask = Task.eventTask(components[2], components[3],
                    components[4], components[1], components[5]);
            taskList.add(newTask);
        } else {
            Task newTask = Task.toDoTask(components[2], components[1],
                    components[3]);
            taskList.add(newTask);
        }
    }

    public int size() {
        return this.taskList.size();
    }

    public Task get(int index) {
        return this.taskList.get(index);
    }

    public void remove(int index) {
        this.taskList.remove(index);
    }

    public void add(Task task) {
        this.taskList.add(task);
    }

    /**
     * Returns a string representation of the task list.
     * Each task is numbered and displayed in the format "index. task".
     *
     * @return a formatted string representing the task list
     */
    public Task find(String taskDescription) throws TaskNotFoundException {
        for (int i = 0; i < this.size(); i++) {
            Task task = this.taskList.get(i);
            String name = task.getName();
            if (name.contains(taskDescription)) {
                return task;
            }
        }

        throw new TaskNotFoundException("Sorry, Derek searched high and low but could not find your task!"
                + "Maybe you are the problem!");
    }

    @Override
    public String toString() {
        String list = "";
        for (int i = 0; i < taskList.size(); i++) {
            list += String.format((i + 1) + ". " + taskList.get(i).toString() + "\n");
        }
        return list;
    }

}

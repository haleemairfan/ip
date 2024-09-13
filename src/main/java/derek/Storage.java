package derek;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;

import derek.task.DeadlineTask;
import derek.task.EventTask;
import derek.task.Task;
import derek.task.TaskList;
import java.time.format.DateTimeFormatter;



/**
 * The {@code Storage} class handles the reading and writing of task data to and from a file.
 * It interacts with the {@code TaskList} to manage tasks persistently.
 */
public class Storage {
    private String dataFilePathName = "./data/derek.txt";
    private TaskList taskList;

    /**
     * Constructs a {@code Storage} object with the specified {@code TaskList}.
     *
     * @param taskList the task list that will be managed by this storage
     */
    public Storage(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Reads the task data from the file specified by {@code dataFilePathName} and populates the task list.
     * If the file does not exist or is a directory, a {@code FileNotFoundException} is thrown.
     * The file is then overwritten to clear its contents.
     *
     * @return the populated {@code TaskList}
     */
    public TaskList readFile() {
        try {
            File tasks = new File(dataFilePathName);
            if (!tasks.exists()) {
                throw new FileNotFoundException("Data file not found");
            } else if (tasks.isDirectory()) {
                throw new FileNotFoundException("Path is a directory");
            }
            Scanner getList = new Scanner(tasks);
            while (getList.hasNext()) {
                String task = getList.nextLine();
                taskList.populateTaskList(task);
            }
            this.overrideFile();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return taskList;

    }

    /**
     * Overrides the task data file by clearing its contents.
     *
     * @throws IOException if an I/O error occurs during file writing
     */
    public void overrideFile() throws IOException {
        FileWriter writer = new FileWriter(dataFilePathName);
        writer.write("");
        writer.close();

    }

    /**
     * Stores the current tasks from the {@code TaskList} into the data file.
     * Each task is written to the file in a specific format based on its type (e.g., DeadlineTask, EventTask).
     */
    public void storeInFile() {
        try {
            for (int i = 0; i < taskList.size(); i++) {
                Task task = taskList.get(i);
                String isCompleted = task.isCompleted();
                LocalDateTime date = task.getCompletionTime();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a");
                String completionDate = date != null
                                        ? date.format(formatter)
                                        : "Incomplete";
                if (task instanceof DeadlineTask) {
                    DeadlineTask deadlineTask = (DeadlineTask) task;
                    String deadline = deadlineTask.getDeadline();
                    String taskName = deadlineTask.getName();
                    writeToFile("D|"
                            + isCompleted
                            + "|"
                            + taskName
                            + "|"
                            + deadline
                            + "|"
                            + completionDate
                            + "\n");
                } else if (task instanceof EventTask) {
                    EventTask eventTask = (EventTask) task;
                    String startTime = eventTask.getStartTime();
                    String endTime = eventTask.getEndTime();
                    String taskName = eventTask.getName();
                    writeToFile("E|"
                            + isCompleted
                            + "|"
                            + taskName
                            + "|"
                            + startTime
                            + "|"
                            + endTime
                            + "|"
                            + completionDate
                            + "\n");
                } else {
                    String taskName = task.getName();
                    writeToFile("T|"
                            + isCompleted
                            + "|" + taskName
                            + "|" + completionDate
                            + "\n");
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Writes a given string to the task data file.
     *
     * @param textToAdd the string to be added to the file
     * @throws IOException if an I/O error occurs during file writing
     */
    public void writeToFile(String textToAdd) throws IOException {
        FileWriter writer = new FileWriter(dataFilePathName, true);
        writer.write(textToAdd);
        writer.close();

    }

    public TaskList getTaskList() {
        assert taskList != null : "TaskList should not be null when accessed";
        return this.taskList;
    }

    public int getTaskListSize() {
        assert this.taskList.size() >= 0 : "TaskList size should not be negative";
        return this.taskList.size();
    }



}


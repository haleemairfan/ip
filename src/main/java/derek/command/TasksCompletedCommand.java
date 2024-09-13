package derek.command;

import derek.Storage;
import derek.Ui;
import derek.task.Task;
import derek.task.TaskList;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TasksCompletedCommand extends Command {
    LocalDateTime date;
    public TasksCompletedCommand(String command, Storage storage,
                                 Ui ui, LocalDateTime date) {
        super(command, storage, ui);
        this.date = date;
    }

    @Override
    public String execute() {
        TaskList taskList = this.getTaskList();
        TaskList tasksCompleted = new TaskList();
        int sizeOfTaskList = this.getSizeOfTaskList();
        LocalDateTime dateOneWeekBefore = getDateOneWeekBefore();
        for (int i = 0; i < sizeOfTaskList; i++) {
            Task task = taskList.get(i);
            LocalDateTime dateOfCompletion = task.getCompletionTime();
            if (dateOfCompletion != null &&
                isWithinLastWeek(dateOfCompletion, dateOneWeekBefore)) {
                tasksCompleted.add(task);
            }
        }
        Ui ui = this.getUi();
        return ui.returnListOfPastWeek(tasksCompleted);
    }

    public LocalDateTime getDateOneWeekBefore() {
        return this.date.minusDays(7);
    }

    public boolean isWithinLastWeek(LocalDateTime dateOfCompletion,
                                    LocalDateTime dateOneWeekBefore) {
        return (dateOfCompletion.isAfter(dateOneWeekBefore) ||
                    dateOfCompletion.isEqual(dateOneWeekBefore))
                &&
                (dateOfCompletion.isBefore(this.date) ||
                        dateOfCompletion.isEqual(this.date));
    }
}

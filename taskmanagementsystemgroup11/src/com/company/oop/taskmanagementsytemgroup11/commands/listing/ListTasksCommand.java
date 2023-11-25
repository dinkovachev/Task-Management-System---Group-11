package com.company.oop.taskmanagementsytemgroup11.commands.listing;

import com.company.oop.taskmanagementsytemgroup11.commands.BaseCommand;
import com.company.oop.taskmanagementsytemgroup11.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Bug;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Task;

import java.util.List;


public class ListTasksCommand extends BaseCommand {

    private static final String SORT_LIST_BUGS_BY_TITLE = "Sorted tasks by title \n%s";


    public ListTasksCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        //       String priority = parameters.get(0);



        List<Task> listTasksByTitle = getTaskManagementSystemRepository().getSortedListOfTasksByTitle();
        StringBuilder stringBuilder = new StringBuilder();
        for (Task task : listTasksByTitle) {
            stringBuilder.append(task.getAsString());
                            //getTitle())
               //     .append(task.getId()).append(System.lineSeparator());
        }
        return String.format(SORT_LIST_BUGS_BY_TITLE, stringBuilder);
    }
}

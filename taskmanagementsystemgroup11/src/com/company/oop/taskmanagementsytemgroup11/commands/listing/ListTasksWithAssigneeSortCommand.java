package com.company.oop.taskmanagementsytemgroup11.commands.listing;

import com.company.oop.taskmanagementsytemgroup11.commands.BaseCommand;
import com.company.oop.taskmanagementsytemgroup11.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Task;
import com.company.oop.taskmanagementsytemgroup11.utils.ValidationHelpers;

import java.util.List;

public class ListTasksWithAssigneeSortCommand extends BaseCommand {
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    private static final String SORT_LIST_TASKS_WITH_ASSIGNEE_BY_TITLE = "Sorted tasks with assignee by title \n%s";

    public ListTasksWithAssigneeSortCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String assignee = parameters.get(0);
        return listing(assignee);

    }

    public String listing(String title) {
        StringBuilder stringBuilder = new StringBuilder();
        List<Task> listTasksByTitle = getTaskManagementSystemRepository().getFilteredListOfTasksByTitle(title);
        for (Task task : listTasksByTitle) {
            stringBuilder.append(task.getAsString());
        }
        return String.format(SORT_LIST_TASKS_WITH_ASSIGNEE_BY_TITLE, stringBuilder);
    }
}

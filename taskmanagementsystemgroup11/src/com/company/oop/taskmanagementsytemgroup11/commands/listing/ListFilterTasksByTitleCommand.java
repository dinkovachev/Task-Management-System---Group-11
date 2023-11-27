package com.company.oop.taskmanagementsytemgroup11.commands.listing;

import com.company.oop.taskmanagementsytemgroup11.commands.BaseCommand;
import com.company.oop.taskmanagementsytemgroup11.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Task;
import com.company.oop.taskmanagementsytemgroup11.utils.ValidationHelpers;


import java.util.List;


public class ListFilterTasksByTitleCommand extends BaseCommand {
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    private static final String FILTER_LIST_TASK_BY_TITLE = "Filter task by title \n\n%s";


    public ListFilterTasksByTitleCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String title = parameters.get(1);
        return listing(title);
    }

    public String listing(String title) {
        StringBuilder stringBuilder = new StringBuilder();

            List<Task> listTasksByTitle = getTaskManagementSystemRepository().getFilteredListOfTasksByTitle(title);
            for (Task task : listTasksByTitle) {
                stringBuilder.append(task.getAsString());
            }
            return String.format(FILTER_LIST_TASK_BY_TITLE, stringBuilder);
        }
}

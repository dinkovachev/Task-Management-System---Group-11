package com.company.oop.taskmanagementsytemgroup11.commands.listing;

import com.company.oop.taskmanagementsytemgroup11.commands.BaseCommand;
import com.company.oop.taskmanagementsytemgroup11.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Bug;
import com.company.oop.taskmanagementsytemgroup11.utils.ValidationHelpers;

import java.util.List;

public class ListBugsSortByPriorityCommand extends BaseCommand {
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 0;

    private static final String SORT_LIST_BUGS_BY_PRIORITY = "Sorted bugs by priority \n\n%s";

    public ListBugsSortByPriorityCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        return listing();
    }

    public String listing() {
        StringBuilder stringBuilder = new StringBuilder();
        List<Bug> sortedListOfBugsByPriority = getTaskManagementSystemRepository().getSortedListOfBugsByPriority();
        for (Bug bug : sortedListOfBugsByPriority) {
            stringBuilder.append(bug.getAsString()).append("\nPriority: ").append(bug.getPriority()).append("\n\n");
        }
        return String.format(SORT_LIST_BUGS_BY_PRIORITY, stringBuilder);

    }
}



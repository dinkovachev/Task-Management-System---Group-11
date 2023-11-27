package com.company.oop.taskmanagementsytemgroup11.commands.listing;

import com.company.oop.taskmanagementsytemgroup11.commands.BaseCommand;
import com.company.oop.taskmanagementsytemgroup11.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Bug;
import com.company.oop.taskmanagementsytemgroup11.utils.ValidationHelpers;

import java.util.List;

public class ListBugsSortBySeverityCommand extends BaseCommand {
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 0;
    private static final String SORT_LIST_BUGS_BY_SEVERITY = "Sorted bugs by assignee \n\n%s";

    public ListBugsSortBySeverityCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        return listing();
    }

    public String listing() {
        StringBuilder stringBuilder = new StringBuilder();
        List<Bug> sortedListOfBugsBySeverity = getTaskManagementSystemRepository().getSortedListOfBugsBySeverity();
        for (Bug bug : sortedListOfBugsBySeverity) {
            stringBuilder.append(bug.getAsString()).append("\nSeverity: ").append(bug.getPriority()).append("\n\n");
        }
        return String.format(SORT_LIST_BUGS_BY_SEVERITY, stringBuilder);
    }
}


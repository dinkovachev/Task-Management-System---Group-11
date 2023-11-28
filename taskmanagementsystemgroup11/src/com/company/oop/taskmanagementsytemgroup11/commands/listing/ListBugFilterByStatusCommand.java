package com.company.oop.taskmanagementsytemgroup11.commands.listing;

import com.company.oop.taskmanagementsytemgroup11.commands.BaseCommand;
import com.company.oop.taskmanagementsytemgroup11.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Bug;
import com.company.oop.taskmanagementsytemgroup11.models.enums.Status;
import com.company.oop.taskmanagementsytemgroup11.utils.ParsingHelpers;
import com.company.oop.taskmanagementsytemgroup11.utils.ValidationHelpers;

import java.util.List;

import static java.lang.String.format;

public class ListBugFilterByStatusCommand extends BaseCommand {
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;

    public ListBugFilterByStatusCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        Status status = ParsingHelpers.tryParseEnum(parameters.get(0), Status.class);

        return FilterBugByStatus(status);
    }

    public String FilterBugByStatus(Status status) {
        StringBuilder stringBuilder = new StringBuilder();
        List<Bug> filteredBugListByStatus = getTaskManagementSystemRepository().getFilteredBugListByStatus(status);

        for (Bug bug : filteredBugListByStatus) {
            stringBuilder.append(bug.getAsString()).append("\nStatus: ").append(bug.getStatus()).append("\n\n");
        }

        stringBuilder.setLength(stringBuilder.length() - 1);
        stringBuilder.setLength(stringBuilder.length() - 1);

        return format("Filtered bugs by status: \n\n%s", stringBuilder);
    }
}

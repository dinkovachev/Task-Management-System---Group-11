package com.company.oop.taskmanagementsytemgroup11.commands.creation;


import com.company.oop.taskmanagementsytemgroup11.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Bug;
import com.company.oop.taskmanagementsytemgroup11.utils.ParsingHelpers;
import com.company.oop.taskmanagementsytemgroup11.utils.ValidationHelpers;

import java.util.List;

import static java.lang.String.format;

public class ChangeBugStatusCommand extends BaseCommand {

    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    private static final String INVALID_INPUT_MSG = format("Invalid input. Expected a number.");

    public ChangeBugStatusCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String commandLine = parameters.get(0);
        int bugIndex = ParsingHelpers.tryParseInteger(parameters.get(1), INVALID_INPUT_MSG) - 1;

        return changeBugStatus(commandLine, bugIndex);
    }

    private String changeBugStatus(String commandLine, int bugIndex) {
        Bug bug = getTaskManagementSystemRepository().findBugByIndex(bugIndex);

        if (commandLine.equals("advance")) {
            bug.advanceStatus();
        } else if (commandLine.equals("revert")) {
            bug.revertStatus();
        } else {
            throw new IllegalArgumentException("Invalid commandLine");
        }
        return format("Status changed to %s", bug.getStatus());
    }
}

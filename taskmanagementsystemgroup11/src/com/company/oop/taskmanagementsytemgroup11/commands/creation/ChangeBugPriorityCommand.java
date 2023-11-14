package com.company.oop.taskmanagementsytemgroup11.commands.creation;


import com.company.oop.taskmanagementsytemgroup11.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Bug;
import com.company.oop.taskmanagementsytemgroup11.models.enums.Priority;
import com.company.oop.taskmanagementsytemgroup11.utils.ParsingHelpers;
import com.company.oop.taskmanagementsytemgroup11.utils.ValidationHelpers;

import java.util.List;

import static java.lang.String.format;

public class ChangeBugPriorityCommand extends BaseCommand {
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    private static final String INVALID_INPUT_MSG = format("Invalid input. Expected a number.");

    public ChangeBugPriorityCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        Priority priority = ParsingHelpers.tryParseEnum(parameters.get(0), Priority.class);
        int bugIndex = ParsingHelpers.tryParseInteger(parameters.get(1), INVALID_INPUT_MSG) - 1;

        return changeBugPriority(priority, bugIndex);
    }

    private String changeBugPriority(Priority newPriority, int bugIndex) {
        Bug bug = getTaskManagementSystemRepository().findBugByIndex(bugIndex);

        if (newPriority.equals(bug.getPriority())) {
            throw new IllegalArgumentException(format("Priority is already set to %s", bug.getPriority()));
        } else {
            bug.changePriority(newPriority);
            return format("Priority changed to %s", bug.getPriority().toString());
        }
    }
}
package com.company.oop.taskmanagementsytemgroup11.commands.creation;


import com.company.oop.taskmanagementsytemgroup11.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Bug;
import com.company.oop.taskmanagementsytemgroup11.models.enums.Severity;
import com.company.oop.taskmanagementsytemgroup11.utils.ParsingHelpers;
import com.company.oop.taskmanagementsytemgroup11.utils.ValidationHelpers;

import java.util.List;

import static java.lang.String.format;

public class ChangeBugSeverityCommand extends BaseCommand {
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    private static final String INVALID_INPUT_MSG = format("Invalid input. Expected a number.");

    public ChangeBugSeverityCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        Severity severity = ParsingHelpers.tryParseEnum(parameters.get(0), Severity.class);
        int bugIndex = ParsingHelpers.tryParseInteger(parameters.get(1), INVALID_INPUT_MSG) - 1;

        return changeBugSeverity(severity, bugIndex);
    }

    private String changeBugSeverity(Severity newSeverity, int bugIndex) {
        Bug bug = getTaskManagementSystemRepository().findBugByIndex(bugIndex);

        if (newSeverity.equals(bug.getSeverity())) {
            throw new IllegalArgumentException(format("Severity is already set to %s", bug.getSeverity()));
        } else {
            bug.changeSeverity(newSeverity);
            return format("Severity changed to %s", bug.getSeverity().toString());
        }
    }
}
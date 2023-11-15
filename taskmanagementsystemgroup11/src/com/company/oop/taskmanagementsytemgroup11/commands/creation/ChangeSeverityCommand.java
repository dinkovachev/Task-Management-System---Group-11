package com.company.oop.taskmanagementsytemgroup11.commands.creation;

import com.company.oop.taskmanagementsytemgroup11.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Bug;
import com.company.oop.taskmanagementsytemgroup11.models.enums.Severity;
import com.company.oop.taskmanagementsytemgroup11.models.enums.TaskType;
import com.company.oop.taskmanagementsytemgroup11.utils.ParsingHelpers;
import com.company.oop.taskmanagementsytemgroup11.utils.ValidationHelpers;

import java.util.List;

import static java.lang.String.format;

public class ChangeSeverityCommand extends BaseCommand {
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 3;
    private static final String INVALID_INPUT_MSG = format("Invalid input. Expected a number.");

    public ChangeSeverityCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    // Changes SEVERITY, only BUG has SEVERITY
    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        TaskType type = ParsingHelpers.tryParseEnum(parameters.get(0), TaskType.class);
        Severity severity = ParsingHelpers.tryParseEnum(parameters.get(1), Severity.class);
        int taskIndex = ParsingHelpers.tryParseInteger(parameters.get(2), INVALID_INPUT_MSG) - 1;

        return changeSeverity(severity, taskIndex);
    }

    private String changeSeverity(Severity severity, int taskIndex) {
        Bug bug = getTaskManagementSystemRepository().findBugByIndex(taskIndex);

        if (severity.equals(bug.getSeverity())) {
            throw new IllegalArgumentException(format("Severity is already set to %s", bug.getSeverity()));
        } else {
            return format("Severity changed to %s", bug.getSeverity().toString());
        }

    }
}

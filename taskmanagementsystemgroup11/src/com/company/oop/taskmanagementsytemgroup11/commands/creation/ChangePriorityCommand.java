package com.company.oop.taskmanagementsytemgroup11.commands.creation;

import com.company.oop.taskmanagementsytemgroup11.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Bug;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Story;
import com.company.oop.taskmanagementsytemgroup11.models.enums.*;
import com.company.oop.taskmanagementsytemgroup11.utils.ParsingHelpers;
import com.company.oop.taskmanagementsytemgroup11.utils.ValidationHelpers;

import java.util.List;

import static java.lang.String.format;

public class ChangePriorityCommand extends BaseCommand {
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 3;
    private static final String INVALID_INPUT_MSG = format("Invalid input. Expected a number.");

    public ChangePriorityCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    // Changes PRIORITY, only BUG and STORY have PRIORITY

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        TaskType type = ParsingHelpers.tryParseEnum(parameters.get(0), TaskType.class);
        Priority priority = ParsingHelpers.tryParseEnum(parameters.get(1), Priority.class);
        int taskIndex = ParsingHelpers.tryParseInteger(parameters.get(2), INVALID_INPUT_MSG) - 1;

        return changePriority(type, priority, taskIndex);
    }

    private String changePriority(TaskType type, Priority priority, int taskIndex) {
        switch (type) {
            case BUG:
                return changeBugPriority(priority, taskIndex);
            case STORY:
                return changeStoryPriority(priority, taskIndex);
            default:
                return format("%s is invalid task", type);
        }
    }

    private String changeBugPriority(Priority priority, int taskIndex) {
        Bug bug = getTaskManagementSystemRepository().findBugByIndex(taskIndex);

        if (priority.equals(bug.getPriority())) {
            throw new IllegalArgumentException(format("Priority is already set to %s", bug.getPriority()));
        } else {
            bug.changePriority(priority);
            return format("Priority changed to %s", bug.getPriority().toString());
        }
    }

    private String changeStoryPriority(Priority priority, int taskIndex) {
        Story story = getTaskManagementSystemRepository().findStoryByIndex(taskIndex);

        if (priority.equals(story.getPriority())) {
            throw new IllegalArgumentException(format("Priority is already set to %s", story.getPriority()));
        } else {
            story.changePriority(priority);
            return format("Priority changed to %s", story.getPriority().toString());
        }
    }
}
package com.company.oop.taskmanagementsytemgroup11.commands.creation;

import com.company.oop.taskmanagementsytemgroup11.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Bug;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Story;
import com.company.oop.taskmanagementsytemgroup11.models.enums.Priority;
import com.company.oop.taskmanagementsytemgroup11.models.enums.TaskType;
import com.company.oop.taskmanagementsytemgroup11.utils.ParsingHelpers;
import com.company.oop.taskmanagementsytemgroup11.utils.ValidationHelpers;

import java.util.List;

import static java.lang.String.format;

public class ChangePriorityCommand extends BaseCommand {
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 3;
    private static final String INVALID_INPUT_MSG = "Invalid input. Expected a number.";
    private static final String INVALID_TASK_MSG = "You cannot change the priority of %s task type.";

    public ChangePriorityCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    // Changes PRIORITY, only BUG and STORY have PRIORITY // High, Medium, Low

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
                return changeBugPriority(type, priority, taskIndex);
            case STORY:
                return changeStoryPriority(type, priority, taskIndex);
            case FEEDBACK:
            default:
                return format(INVALID_TASK_MSG, type);
        }
    }

    private String changeBugPriority(TaskType type, Priority priority, int taskIndex) {
        Bug bug = getTaskManagementSystemRepository().findBugByTaskIndex(taskIndex);
        getTaskManagementSystemRepository().validateTaskTypeEqualsInputType(type, taskIndex);

        if (priority.equals(bug.getPriority())) {
            throw new IllegalArgumentException(format("Priority is already set to %s.", bug.getPriority()));
        } else {
            bug.changePriority(priority);
            return format("Priority changed to %s.", bug.getPriority());
        }
    }

    private String changeStoryPriority(TaskType type, Priority priority, int taskIndex) {
        Story story = getTaskManagementSystemRepository().findStoryByTaskIndex(taskIndex);
        getTaskManagementSystemRepository().validateTaskTypeEqualsInputType(type, taskIndex); // IMPORTANT!
        if (priority.equals(story.getPriority())) {
            throw new IllegalArgumentException(format("Priority is already set to %s.", story.getPriority()));
        } else {
            story.changePriority(priority);
            return format("Priority changed to %s.", story.getPriority());
        }
    }
}
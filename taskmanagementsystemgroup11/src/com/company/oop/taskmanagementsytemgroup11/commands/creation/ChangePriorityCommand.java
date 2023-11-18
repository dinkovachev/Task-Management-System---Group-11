package com.company.oop.taskmanagementsytemgroup11.commands.creation;

import com.company.oop.taskmanagementsytemgroup11.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Bug;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Story;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Task;
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
        int taskIndex = ParsingHelpers.tryParseInteger(parameters.get(2), INVALID_INPUT_MSG);

        return changePriority(type, priority, taskIndex);
    }

    private String changePriority(TaskType type, Priority priority, int taskIndex) {
        switch (type) {
            case BUG:
                return changeBugPriority(priority, taskIndex);
            case STORY:
                return changeStoryPriority(priority, taskIndex);
            case FEEDBACK:
            default:
                return format(INVALID_TASK_MSG, type);
        }
    }

    private String changeBugPriority(Priority priority, int taskIndex) {
        Task bug = getTaskManagementSystemRepository().findBugByTaskIndex(taskIndex);
        if (priority.equals(((Bug) bug).getPriority())) {
            throw new IllegalArgumentException(format("Size is already set to %s", ((Bug) bug).getPriority()));
        } else{
            ((Bug) bug).changePriority(priority);
            return format("Size changed to %s", ((Bug) bug).getPriority());
        }
    }

    private String changeStoryPriority(Priority priority, int taskIndex) {
        Story story = getTaskManagementSystemRepository().findStoryByIndex(taskIndex);
        if (priority.equals(story.getPriority())) {
            throw new IllegalArgumentException(format("Size is already set to %s", story.getPriority()));
        } else {
            story.changePriority(priority);
            return format("Size changed to %s", story.getPriority());
        }
    }
}
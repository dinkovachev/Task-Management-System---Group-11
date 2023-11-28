package com.company.oop.taskmanagementsytemgroup11.commands.change;

import com.company.oop.taskmanagementsytemgroup11.commands.BaseCommand;
import com.company.oop.taskmanagementsytemgroup11.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Bug;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Feedback;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Story;
import com.company.oop.taskmanagementsytemgroup11.models.enums.TaskType;
import com.company.oop.taskmanagementsytemgroup11.utils.ParsingHelpers;
import com.company.oop.taskmanagementsytemgroup11.utils.ValidationHelpers;

import java.util.List;

import static java.lang.String.format;

public class ChangeStatusCommand extends BaseCommand {
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 3;
    private static final String INVALID_INPUT_MSG = "Invalid input. Expected a number.";
    public static final String INVALID_DIRECTION_MSG = "Invalid direction %s";
    public static final String IS_INVALID_TASK_MESSAGE = "%s is invalid task";
    public static final String ADVANCE_MESSAGE = "advance";
    public static final String REVERT_MESSAGE = "revert";

    public ChangeStatusCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        TaskType type = ParsingHelpers.tryParseEnum(parameters.get(0), TaskType.class);
        String direction = parameters.get(1);
        int taskIndex = ParsingHelpers.tryParseInteger(parameters.get(2), INVALID_INPUT_MSG);

        return changeStatus(type, direction, taskIndex);
    }

    private String changeStatus(TaskType type, String direction, int taskIndex) {
        switch (type) {
            case BUG:
                return changeBugStatus(direction, taskIndex);
            case STORY:
                return changeStoryStatus(direction, taskIndex);
            case FEEDBACK:
                return changeFeedbackStatus(direction, taskIndex);
            default:
                return format(IS_INVALID_TASK_MESSAGE, type);
        }
    }

    private String changeBugStatus(String direction, int taskIndex) {
        Bug bug = getTaskManagementSystemRepository().findBugByTaskIndex(taskIndex);

        if (direction.equalsIgnoreCase(ADVANCE_MESSAGE)) {
            return bug.advanceStatus();
        } else if (direction.equalsIgnoreCase(REVERT_MESSAGE)) {
            return bug.revertStatus();
        } else {
            return format(INVALID_DIRECTION_MSG, direction);
        }
    }

    private String changeStoryStatus(String direction, int taskIndex) {
        Story story = getTaskManagementSystemRepository().findStoryByTaskIndex(taskIndex);

        if (direction.equalsIgnoreCase(ADVANCE_MESSAGE)) {
            return story.advanceStatus();
        } else if (direction.equalsIgnoreCase(REVERT_MESSAGE)) {
            return story.revertStatus();
        } else {
            return format(INVALID_DIRECTION_MSG, direction);
        }
    }

    private String changeFeedbackStatus(String direction, int taskIndex) {
        Feedback feedback = getTaskManagementSystemRepository().findFeedbackByTaskIndex(taskIndex);

        if (direction.equalsIgnoreCase(ADVANCE_MESSAGE)) {
            return feedback.advanceStatus();
        } else if (direction.equalsIgnoreCase(REVERT_MESSAGE)) {
            return feedback.revertStatus();
        } else {
            return format(INVALID_DIRECTION_MSG, direction);
        }
    }
}
package com.company.oop.taskmanagementsytemgroup11.commands.change;

import com.company.oop.taskmanagementsytemgroup11.commands.BaseCommand;
import com.company.oop.taskmanagementsytemgroup11.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Story;
import com.company.oop.taskmanagementsytemgroup11.models.enums.Size;
import com.company.oop.taskmanagementsytemgroup11.models.enums.TaskType;
import com.company.oop.taskmanagementsytemgroup11.utils.ParsingHelpers;
import com.company.oop.taskmanagementsytemgroup11.utils.ValidationHelpers;

import java.util.List;

import static java.lang.String.format;

public class ChangeSizeCommand extends BaseCommand {

    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 3;
    private static final String INVALID_INPUT_MSG = "Invalid input. Expected a number.";
    private static final String INVALID_TASK_MSG = "You cannot change the size of %s task type.";
    public static final String SIZE_IS_ALREADY_SET_TO_MESSAGE = "Size is already set to %s.";
    public static final String SIZE_CHANGED_TO_MESSAGE = "Size changed to %s.";

    public ChangeSizeCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        TaskType type = ParsingHelpers.tryParseEnum(parameters.get(0), TaskType.class);
        validateTaskType(type);
        Size size = ParsingHelpers.tryParseEnum(parameters.get(1), Size.class);
        int taskIndex = ParsingHelpers.tryParseInteger(parameters.get(2), INVALID_INPUT_MSG);

        return changeStorySize(type, size, taskIndex);
    }

    private String changeStorySize(TaskType type, Size size, int taskIndex) {
        Story story = getTaskManagementSystemRepository().findStoryByTaskIndex(taskIndex);
        if (size.equals(story.getSize())) {
            throw new IllegalArgumentException(format(SIZE_IS_ALREADY_SET_TO_MESSAGE, story.getSize()));
        } else {
            story.changeSize(taskIndex, size);
            return format(SIZE_CHANGED_TO_MESSAGE, story.getSize());
        }
    }

    private void validateTaskType(TaskType type) {
        if (!type.equals(TaskType.STORY)) {
            throw new IllegalArgumentException(format(INVALID_TASK_MSG, type));
        }
    }
}
package com.company.oop.taskmanagementsytemgroup11.commands.creation;

import com.company.oop.taskmanagementsytemgroup11.core.TaskManagementSystemRepositoryImpl;
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

    public ChangeSizeCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    // Changes SIZE, Only Story has SIZE // Large, Medium, Small

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        TaskType type = ParsingHelpers.tryParseEnum(parameters.get(0), TaskType.class);
        validateTaskType(type);
        Size size = ParsingHelpers.tryParseEnum(parameters.get(1), Size.class);
        int taskIndex = ParsingHelpers.tryParseInteger(parameters.get(2), INVALID_INPUT_MSG) - 1;

        return changeStorySize(type, size, taskIndex);
    }

    private String changeStorySize(TaskType type, Size size, int taskIndex) {
        Story story = getTaskManagementSystemRepository().findStoryByTaskIndex(taskIndex);
        getTaskManagementSystemRepository().validateTaskTypeStory(type,taskIndex); // IMPORTANT!
        if (size.equals(story.getSize())) {
            throw new IllegalArgumentException(format("Size is already set to %s", story.getSize()));
        } else {
            story.changeSize(size);
            return format("Size changed to %s", story.getSize().toString());
        }
    }

    //    private String changeSize(Size size, int taskIndex) {
//    Story story = getTaskManagementSystemRepository().findStoryByIndex(taskIndex);

    //
//        if (size.equals(story.getSize())) {
//            throw new IllegalArgumentException(format("Size is already set to %s", story.getSize()));
//        } else {
//            story.changeSize(size);
//            return format("Size changed to %s", story.getSize().toString());
//        }
//    }
//
    private void validateTaskType(TaskType type) {
        if (!type.equals(TaskType.STORY)) {
            throw new IllegalArgumentException(format(INVALID_TASK_MSG, type));
        }
    }
}
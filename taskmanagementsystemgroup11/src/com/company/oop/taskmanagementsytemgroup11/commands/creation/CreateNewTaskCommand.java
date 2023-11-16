package com.company.oop.taskmanagementsytemgroup11.commands.creation;

import com.company.oop.taskmanagementsytemgroup11.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Bug;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Feedback;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Story;
import com.company.oop.taskmanagementsytemgroup11.models.enums.Priority;
import com.company.oop.taskmanagementsytemgroup11.models.enums.Severity;
import com.company.oop.taskmanagementsytemgroup11.models.enums.Size;
import com.company.oop.taskmanagementsytemgroup11.models.enums.TaskType;
import com.company.oop.taskmanagementsytemgroup11.utils.ParsingHelpers;
import com.company.oop.taskmanagementsytemgroup11.utils.ValidationHelpers;

import java.util.List;

import static java.lang.String.format;


public class CreateNewTaskCommand extends BaseCommand {

    private static final int EXPECTED_ARGUMENTS_COUNT_FEEDBACK = 4;
    private static final int EXPECTED_ARGUMENTS_COUNT_STORY = 6;
    private static final int EXPECTED_ARGUMENTS_COUNT_BUG = 7;
    private static final String INVALID_INPUT_MSG = format("Invalid input. Expected a number.");
    private static final String NEW_TASK_CREATED_MSG = "New %s with id %s created.";
    private static final String INVALID_ARGUMENT_COUNT_MSG = "Incorrect number of arguments.";

    public CreateNewTaskCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    protected String executeCommand(List<String> parameters) {
        switch (parameters.size()) {
            case EXPECTED_ARGUMENTS_COUNT_FEEDBACK:
                TaskType typeFeedback = ParsingHelpers.tryParseEnum(parameters.get(0), TaskType.class);
                ValidationHelpers.validateTaskType(typeFeedback, EXPECTED_ARGUMENTS_COUNT_FEEDBACK);
                String titleFeedback = parameters.get(1);
                String descriptionFeedback = parameters.get(2);
                int rating = ParsingHelpers.tryParseInteger(parameters.get(3), INVALID_INPUT_MSG) - 1;

                return createNewFeedback(
                        typeFeedback, titleFeedback, descriptionFeedback, rating);

            case EXPECTED_ARGUMENTS_COUNT_STORY:
                TaskType typeStory = ParsingHelpers.tryParseEnum(parameters.get(0), TaskType.class);
                ValidationHelpers.validateTaskType(typeStory, EXPECTED_ARGUMENTS_COUNT_STORY);
                String titleStory = parameters.get(1);
                String descriptionStory = parameters.get(2);
                Priority priorityStory = ParsingHelpers.tryParseEnum(parameters.get(3), Priority.class);
                Size size = ParsingHelpers.tryParseEnum(parameters.get(4), Size.class);
                String assigneeStory = parameters.get(5);

                return createNewStory(
                        typeStory, titleStory, descriptionStory, priorityStory, size, assigneeStory);

            case EXPECTED_ARGUMENTS_COUNT_BUG:
                TaskType typeBug = ParsingHelpers.tryParseEnum(parameters.get(0), TaskType.class);
                ValidationHelpers.validateTaskType(typeBug, EXPECTED_ARGUMENTS_COUNT_BUG);
                String titleBug = parameters.get(1);
                String descriptionBug = parameters.get(2);
                String stepsToReproduce = parameters.get(3);
                Priority priorityBug = ParsingHelpers.tryParseEnum(parameters.get(4), Priority.class);
                Severity severityBug = ParsingHelpers.tryParseEnum(parameters.get(5), Severity.class);
                String assigneeBug = parameters.get(6);

                return createNewBug(
                        typeBug, titleBug, descriptionBug, stepsToReproduce, priorityBug, severityBug, assigneeBug);

            default:
                throw new IllegalArgumentException(INVALID_ARGUMENT_COUNT_MSG);
        }
    }

    private String createNewFeedback(TaskType typeFeedback, String titleFeedback, String descriptionFeedback, int rating) {
        Feedback feedback = getTaskManagementSystemRepository().createFeedback(
                typeFeedback, titleFeedback, descriptionFeedback, rating);
        return format(NEW_TASK_CREATED_MSG, typeFeedback, feedback.getId());
    }

    private String createNewStory(
            TaskType typeStory, String titleStory, String descriptionStory, Priority priorityStory, Size size,
            String assigneeStory) {
        Story story = getTaskManagementSystemRepository().createStory
                (typeStory, titleStory, descriptionStory, priorityStory, size, assigneeStory);
        return format(NEW_TASK_CREATED_MSG, typeStory, story.getId());
    }

    private String createNewBug(TaskType typeBug, String titleBug, String descriptionBug, String stepsToReproduce,
                                Priority priorityBug, Severity severityBug, String assigneeBug) {

        Bug bug = getTaskManagementSystemRepository().createBug(
                typeBug, titleBug, descriptionBug, stepsToReproduce, priorityBug, severityBug, assigneeBug);
        return format(NEW_TASK_CREATED_MSG, typeBug, bug.getId());
    }
}
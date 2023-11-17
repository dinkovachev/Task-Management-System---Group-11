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

import java.util.List;

import static java.lang.String.format;


public class CreateNewTaskCommand extends BaseCommand {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 7;
    public static final String INVALID_INPUT_MSG = format("Invalid input. Expected a number.");

    public CreateNewTaskCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    protected String executeCommand(List<String> parameters) {
        switch (parameters.size()) {
            case 4:
                TaskType type = ParsingHelpers.tryParseEnum(parameters.get(0), TaskType.class);
                String titleFeedback = parameters.get(1);
                String descriptionFeedback = parameters.get(2);
                int rating = ParsingHelpers.tryParseInteger(parameters.get(3), INVALID_INPUT_MSG) - 1;

                return createNewFeedback(
                        type, titleFeedback, descriptionFeedback, rating);

            case 6:
                int indexStory = ParsingHelpers.tryParseInteger(parameters.get(0), INVALID_INPUT_MSG) - 1;
                String titleStory = parameters.get(1);
                String descriptionStory = parameters.get(2);
                Priority priorityStory = ParsingHelpers.tryParseEnum(parameters.get(3), Priority.class);
                Size size = ParsingHelpers.tryParseEnum(parameters.get(4), Size.class);
                String assigneeStory = parameters.get(5);

                return createNewStory(
                        indexStory, titleStory, descriptionStory, priorityStory, size, assigneeStory);

            case 7:
                int indexBug = ParsingHelpers.tryParseInteger(parameters.get(0), INVALID_INPUT_MSG) - 1;
                String titleBug = parameters.get(1);
                String descriptionBug = parameters.get(2);
                String stepsToReproduce = parameters.get(3);
                Priority priorityBug = ParsingHelpers.tryParseEnum(parameters.get(4), Priority.class);
                Severity severityBug = ParsingHelpers.tryParseEnum(parameters.get(5), Severity.class);
                String assigneeBug = parameters.get(6);

                return createNewBug(
                        indexBug, titleBug, descriptionBug, stepsToReproduce, priorityBug, severityBug, assigneeBug);

            default:
                throw new IllegalArgumentException("Invalid commandLine");
        }
    }

    private String createNewFeedback(TaskType type , String titleFeedback, String descriptionFeedback, int rating) {
        Feedback feedback = getTaskManagementSystemRepository().createFeedback(
                type, titleFeedback, descriptionFeedback, rating);
        return format("New feedback created %s.", feedback.getId());
    }

    private String createNewStory(
            int indexStory, String titleStory, String descriptionStory, Priority priorityStory, Size size,
            String assigneeStory) {
        Story story = getTaskManagementSystemRepository().createStory
                (indexStory, titleStory, descriptionStory, priorityStory, size, assigneeStory);
        return format("New story created %s.", story.getId());
    }

    private String createNewBug(int indexBug, String titleBug, String descriptionBug, String stepsToReproduce,
                                Priority priorityBug, Severity severityBug, String assigneeBug) {

        Bug bug = getTaskManagementSystemRepository().createBug(
                indexBug, titleBug, descriptionBug, stepsToReproduce, priorityBug, severityBug, assigneeBug);
        return format("New bug created %s.", bug.getId());
    }
}
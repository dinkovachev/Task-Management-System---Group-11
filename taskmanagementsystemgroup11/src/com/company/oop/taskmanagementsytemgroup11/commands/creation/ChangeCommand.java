package com.company.oop.taskmanagementsytemgroup11.commands.creation;

import com.company.oop.taskmanagementsytemgroup11.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Bug;
import com.company.oop.taskmanagementsytemgroup11.models.enums.*;
import com.company.oop.taskmanagementsytemgroup11.utils.ParsingHelpers;
import com.company.oop.taskmanagementsytemgroup11.utils.ValidationHelpers;

import java.util.List;

import static java.lang.String.format;

public class ChangeCommand extends BaseCommand {
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 4;
    private static final String INVALID_INPUT_MSG = format("Invalid input. Expected a number.");

    public ChangeCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    // "Change Bug Status advance 1"
    // "Change Feedback Severity Major 1"

    // Changes PRIORITY


    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        TaskType type = ParsingHelpers.tryParseEnum(parameters.get(0), TaskType.class);
        String task = parameters.get(1);
        Priority priority = ParsingHelpers.tryParseEnum(parameters.get(2), Priority.class);
        int taskIndex = ParsingHelpers.tryParseInteger(parameters.get(3), INVALID_INPUT_MSG) - 1;

        return changePriority(type, task, priority, taskIndex);
    }

    private String changePriority(TaskType type, String task, Priority priority, int taskIndex) {
        switch (type) {
            case BUG:
                if (priority)
                return changeBugPriority(priority, taskIndex);
            case STORY:
                return "implement2";
            default:
                return "no";
        }
    }

    private String changeBugPriority(Priority priority, int bugIndex) {
        Bug bug = getTaskManagementSystemRepository().findBugByIndex(bugIndex);

        if (priority.equals(bug.getPriority())) {
            throw new IllegalArgumentException(format("Priority is already set to %s", bug.getPriority()));
        } else {
            bug.changePriority(priority);
            return format("Priority changed to %s", bug.getPriority().toString());
        }
    }


    // Changes SEVERITY
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        TaskType type = ParsingHelpers.tryParseEnum(parameters.get(0), TaskType.class);
        String attribute = parameters.get(1);
        Severity severity = ParsingHelpers.tryParseEnum(parameters.get(2), Severity.class);
        int taskIndex = ParsingHelpers.tryParseInteger(parameters.get(3), INVALID_INPUT_MSG) - 1;

        return changeSeverity(type, attribute, severity, taskIndex);
    }

    private String changeSeverity(TaskType type, String attribute, Severity severity, int taskIndex) {
        // Only bug has Severity
    }

    // Changes STATUS
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        TaskType type = ParsingHelpers.tryParseEnum(parameters.get(0), TaskType.class);
        String attribute = parameters.get(1);
        Status status = ParsingHelpers.tryParseEnum(parameters.get(2), Status.class);
        int taskIndex = ParsingHelpers.tryParseInteger(parameters.get(3), INVALID_INPUT_MSG) - 1;

        return changeStatus(type, attribute, status, taskIndex);
    }

    private String changeStatus(TaskType type, String attribute, Status status, int taskIndex) {
        switch (type) {
            case BUG:
                return "implement1";
            case FEEDBACK:
                return "implement2";
            default:
                return "no";
        }
    }

    // Changes SIZE
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        TaskType type = ParsingHelpers.tryParseEnum(parameters.get(0), TaskType.class);
        String attribute = parameters.get(1);
        Size size = ParsingHelpers.tryParseEnum(parameters.get(2), Size.class);
        int taskIndex = ParsingHelpers.tryParseInteger(parameters.get(3), INVALID_INPUT_MSG) - 1;

        return changeSize(type, attribute, size, taskIndex);
    }

    private String changeSize(TaskType type, String attribute, Size size, int taskIndex) {
        // Only Story has size;
    }

    // Changes RATING
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        TaskType type = ParsingHelpers.tryParseEnum(parameters.get(0), TaskType.class);
        String attribute = parameters.get(1);
        int rating = ParsingHelpers.tryParseInteger(parameters.get(2), INVALID_INPUT_MSG) - 1;
        int taskIndex = ParsingHelpers.tryParseInteger(parameters.get(3), INVALID_INPUT_MSG) - 1;

        return changeRating(type, attribute, rating, taskIndex);
    }

    private String changeRating(TaskType type, String attribute, int rating, int taskIndex) {
        // Only Feedback has Rating
    }


//    private String changeAttribute(TaskType type, String attribute, int taskIndex) {
//        switch (type) {
//            case BUG:
//                switch (attribute) {
//                    case "Priority":
//                        return changeBugPriority(type, attribute, newValuePriority, taskIndex);
//
//                }
//
//
//            case STORY:
//                return
//
//        }
//    }

//    private String changeBugPriority(TaskType type, Priority newPriority, int bugIndex) {
//        Bug bug = getTaskManagementSystemRepository().findBugByIndex(bugIndex);
//
//        if (newPriority.equals(bug.getPriority())) {
//            throw new IllegalArgumentException(format("Priority is already set to %s", bug.getPriority()));
//        } else {
//            bug.changePriority(newPriority);
//            return format("Priority changed to %s", bug.getPriority().toString());
//        }
//    }


}

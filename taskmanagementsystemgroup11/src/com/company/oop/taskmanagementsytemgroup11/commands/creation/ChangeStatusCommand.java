//package com.company.oop.taskmanagementsytemgroup11.commands.creation;
//
//import com.company.oop.taskmanagementsytemgroup11.core.contracts.TaskManagementSystemRepository;
//import com.company.oop.taskmanagementsytemgroup11.models.contracts.Bug;
//import com.company.oop.taskmanagementsytemgroup11.models.contracts.Feedback;
//import com.company.oop.taskmanagementsytemgroup11.models.contracts.Story;
//import com.company.oop.taskmanagementsytemgroup11.models.enums.TaskType;
//import com.company.oop.taskmanagementsytemgroup11.utils.ParsingHelpers;
//import com.company.oop.taskmanagementsytemgroup11.utils.ValidationHelpers;
//
//import java.util.List;
//
//import static java.lang.String.format;
//
//public class ChangeStatusCommand extends BaseCommand {
//    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 3;
//    private static final String INVALID_INPUT_MSG = format("Invalid input. Expected a number.");
//
//    public ChangeStatusCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
//        super(taskManagementSystemRepository);
//    }
//
//    // Change STATUS -> advance/revert only
//    @Override
//    protected String executeCommand(List<String> parameters) {
//        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
//        TaskType type = ParsingHelpers.tryParseEnum(parameters.get(0), TaskType.class);
//        String direction = parameters.get(1);
//        int taskIndex = ParsingHelpers.tryParseInteger(parameters.get(2), INVALID_INPUT_MSG);
//
//        return changeStatus(type, direction, taskIndex);
//    }
//
//    private String changeStatus(TaskType type, String direction, int taskIndex) {
//        switch (type) {
//            case BUG:
//                return changeBugStatus(direction, taskIndex);
//            case STORY:
//                return changeStoryStatus(direction, taskIndex);
//            case FEEDBACK:
//                return changeFeedbackStatus(direction, taskIndex);
//            default:
//                return format("%s is invalid task", type);
//        }
//    }
//
//    private String changeBugStatus(String direction, int taskIndex) {
//        Bug bug = getTaskManagementSystemRepository().findBugByIndex(taskIndex);
//
//        if (direction.equalsIgnoreCase("advance")) {
//            bug.advanceStatus();
//        } else if (direction.equalsIgnoreCase("revert")) {
//            bug.revertStatus();
//        } else {
//            throw new IllegalArgumentException("Invalid direction");
//        }
//        return format("Bug status changed to %s", bug.getStatus());
//    }
//
////    private String changeStoryStatus(String direction, int taskIndex) {
////        Story story = getTaskManagementSystemRepository().findStoryByIndex(taskIndex);
////
////        if (direction.equalsIgnoreCase("advance")) {
////            story.advanceStatus();
////        } else if (direction.equalsIgnoreCase("revert")) {
////            story.revertStatus();
////        } else {
////            throw new IllegalArgumentException("Invalid direction");
////        }
////        return format("Story status changed to %s", story.getStatus());
////    }
//
//    private String changeFeedbackStatus(String direction, int taskIndex) {
//        Feedback feedback = getTaskManagementSystemRepository().findFeedbackByIndex(taskIndex);
//
//        if (direction.equalsIgnoreCase("advance")) {
//            feedback.advanceStatus();
//        } else if (direction.equalsIgnoreCase("revert")) {
//            feedback.revertStatus();
//        } else {
//            throw new IllegalArgumentException("Invalid direction");
//        }
//        return format("Feedback status changed to %s", feedback.getStatus());
//    }
//}

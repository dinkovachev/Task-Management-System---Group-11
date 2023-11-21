//package com.company.oop.taskmanagementsytemgroup11.commands.creation;
//
//import com.company.oop.taskmanagementsytemgroup11.commands.BaseCommand;
//import com.company.oop.taskmanagementsytemgroup11.core.contracts.TaskManagementSystemRepository;
//import com.company.oop.taskmanagementsytemgroup11.models.contracts.Board;
//import com.company.oop.taskmanagementsytemgroup11.models.contracts.Bug;
//import com.company.oop.taskmanagementsytemgroup11.models.contracts.Feedback;
//import com.company.oop.taskmanagementsytemgroup11.models.enums.Priority;
//import com.company.oop.taskmanagementsytemgroup11.models.enums.Severity;
//import com.company.oop.taskmanagementsytemgroup11.models.enums.TaskType;
//import com.company.oop.taskmanagementsytemgroup11.utils.ParsingHelpers;
//import com.company.oop.taskmanagementsytemgroup11.utils.ValidationHelpers;
//
//import java.util.List;
//
//import static java.lang.String.format;
//
//public class CreateNewTaskCommand extends BaseCommand {
//
//    public static final int EXPECTED_ARGUMENTS_COUNT_FEEDBACK = 6;
//    public static final int EXPECTED_ARGUMENTS_COUNT_STORY = 8;
//    public static final int EXPECTED_ARGUMENTS_COUNT_BUG = 9;
//    private static final String INVALID_INPUT_MSG = "Invalid input. Expected a number.";
//    private static final String NEW_TASK_CREATED_MSG = "New %s with id %s created.";
//    private static final String INVALID_ARGUMENT_COUNT_MSG = "Incorrect number of arguments.";
//
//    public CreateNewTaskCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
//        super(taskManagementSystemRepository);
//    }

//    protected String executeCommand(List<String> parameters) {
//        switch (parameters.size()) {
//            case EXPECTED_ARGUMENTS_COUNT_FEEDBACK:
//                TaskType typeFeedback = ParsingHelpers.tryParseEnum(parameters.get(0), TaskType.class);
//                ValidationHelpers.validateTaskType(typeFeedback, EXPECTED_ARGUMENTS_COUNT_FEEDBACK);
//                String titleFeedback = parameters.get(1);
//                String descriptionFeedback = parameters.get(2);
//                int rating = ParsingHelpers.tryParseInteger(parameters.get(3), INVALID_INPUT_MSG);
//                String teamNameFeedback = parameters.get(4);
//                validateTeamName(teamNameFeedback);
//                String boardFeedback = parameters.get(5);
//                validateBoardName(boardFeedback);
//                int taskIndexFeedback = getTaskManagementSystemRepository().getLastId();
//
//                return createNewFeedback(
//                        typeFeedback, titleFeedback, descriptionFeedback, rating, taskIndexFeedback, boardFeedback, boardFeedback);

//            case EXPECTED_ARGUMENTS_COUNT_STORY:
//                TaskType typeStory = ParsingHelpers.tryParseEnum(parameters.get(0), TaskType.class);
//                ValidationHelpers.validateTaskType(typeStory, EXPECTED_ARGUMENTS_COUNT_STORY);
//                String titleStory = parameters.get(1);
//                String descriptionStory = parameters.get(2);
//                Priority priorityStory = ParsingHelpers.tryParseEnum(parameters.get(3), Priority.class);
//                Size size = ParsingHelpers.tryParseEnum(parameters.get(4), Size.class);
//                String assigneeStory = parameters.get(5);
//                validateMemberExists(assigneeStory);
//                String teamNameStory = parameters.get(6);
//                validateTeamName(teamNameStory);
//                String boardStory = parameters.get(7);
//                validateBoardName(boardStory);
//                int taskIndexStory = getTaskManagementSystemRepository().getLastId();
//
//                return createNewStory(typeStory, titleStory, descriptionStory, priorityStory, size, assigneeStory,
//                        taskIndexStory, boardStory, boardStory);

//            case EXPECTED_ARGUMENTS_COUNT_BUG:
//                TaskType typeBug = ParsingHelpers.tryParseEnum(parameters.get(0), TaskType.class);
//                ValidationHelpers.validateTaskType(typeBug, EXPECTED_ARGUMENTS_COUNT_BUG);
//                String titleBug = parameters.get(1);
//                String descriptionBug = parameters.get(2);
//                String stepsToReproduce = parameters.get(3);
//                Priority priorityBug = ParsingHelpers.tryParseEnum(parameters.get(4), Priority.class);
//                Severity severityBug = ParsingHelpers.tryParseEnum(parameters.get(5), Severity.class);
//                String assigneeBug = parameters.get(6);
//                validateMemberExists(assigneeBug);
//                String teamNameBug = parameters.get(7);
//                validateTeamName(teamNameBug);
//                String boardBug = parameters.get(8);
//                validateBoardName(boardBug);
//                int taskIndexBug = getTaskManagementSystemRepository().getLastId();
//
//                return createNewBug(typeBug, titleBug, descriptionBug, stepsToReproduce, priorityBug, severityBug,
//                        assigneeBug, taskIndexBug, boardBug, boardBug);
//
//            default:
//                throw new IllegalArgumentException(INVALID_ARGUMENT_COUNT_MSG);
//        }
//   }
//
//    private String createNewFeedback(TaskType typeFeedback, String titleFeedback, String descriptionFeedback,
//                                     int rating, int taskIndexFeedback, String teamnameFeedback, String boardFeedback) {
//        Feedback feedback = getTaskManagementSystemRepository().createFeedback(typeFeedback, titleFeedback,
//                descriptionFeedback, rating, taskIndexFeedback, teamnameFeedback, boardFeedback);
//        Board board = getTaskManagementSystemRepository().getBoardByName(boardFeedback);
//        board.addTask(feedback);
//
//        return format(NEW_TASK_CREATED_MSG, typeFeedback, feedback.getId());
//    }
//
////    private String createNewStory(
////            TaskType typeStory, String titleStory, String descriptionStory, Priority priorityStory, Size size,
////            String assigneeStory, int taskIndexStory, String teamnameStory, String boardStory) {
////        Story story = getTaskManagementSystemRepository().createStory(typeStory, titleStory, descriptionStory,
////                priorityStory, size, assigneeStory, taskIndexStory, teamnameStory, boardStory);
////        Board board = getTaskManagementSystemRepository().getBoardByName(boardStory);
////
////        board.addTask(story);
////        return format(NEW_TASK_CREATED_MSG, typeStory, story.getId());
////    }
//
////    private String createNewBug(TaskType typeBug, String titleBug, String descriptionBug, String stepsToReproduce,
////                                Priority priorityBug, Severity severityBug, String assigneeBug, int taskIndexStory,
////                                String teamnameBug, String boardBug) {
////
////        Bug bug = getTaskManagementSystemRepository().createBug(
////                typeBug, titleBug, descriptionBug, stepsToReproduce, priorityBug, severityBug, assigneeBug,
////                taskIndexStory, teamnameBug, boardBug);
////        Board board = getTaskManagementSystemRepository().getBoardByName(boardBug);
////        board.addTask(bug);
////
////        return format(NEW_TASK_CREATED_MSG, typeBug, bug.getId());
////    }
//
//    private void validateMemberExists(String assignee) {
//        if (!getTaskManagementSystemRepository().memberExist(assignee)) {
//            throw new IllegalArgumentException(format("Member with username %s does not exist.", assignee));
//        }
//    }
//
//    private void validateTeamName(String teamname) {
//        if (!getTaskManagementSystemRepository().teamExist(teamname)) {
//            throw new IllegalArgumentException(format("Team with name %s does not exist.", teamname));
//        }
//    }
//
//    private void validateBoardName(String boardname) {
//        if (!getTaskManagementSystemRepository().boardExist(boardname)) {
//            throw new IllegalArgumentException(format("Board with name %s does not exist.", boardname));
//        }
//    }
//}
package com.company.oop.taskmanagementsytemgroup11.commands.creation;

import com.company.oop.taskmanagementsytemgroup11.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Comment;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Task;
import com.company.oop.taskmanagementsytemgroup11.utils.ParsingHelpers;
import com.company.oop.taskmanagementsytemgroup11.utils.ValidationHelpers;

import java.util.List;

public class AddCommentCommand extends BaseCommand {

    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 3;
    private final static String COMMENT_ADDED_SUCCESSFULLY = "%s added comment successfully!";
    private static final String INVALID_INPUT_MESSAGE = "Invalid input. Expected a number.";
    private static final String TASK_DOES_NOT_EXIST = "Task doesn't exist";


    public AddCommentCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }


    @Override
//    protected String executeCommand(List<String> parameters) {
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String content = parameters.get(0);
        String author = parameters.get(1);
        //ToDo - Dinko
        // think how to implement the index to recognize if it is added for a bug, story or feedback
        int taskIndex = ParsingHelpers.tryParseInteger(parameters.get(2), INVALID_INPUT_MESSAGE) - 1;
        return addComment(content, author, taskIndex);
    }

    //ToDo(Double check if it is working correctly) - Dinko
    // this command checks to which tasks which member from the team can add comment
    private String addComment(String content, String author, int taskIndex) {
        //ToDo(Done) - Dinko
        // 1. Added method in TaskManagementSystemRepository(Interface) to find task by ID so that you can add comments to it
        // 2. Added method in Task interface to getAllTasks so you can find the task you want to add a comment with ID
        // 3. Added method in Task interface to addComments to tasks
        // 4. Added method in Members interface void addComment(Comment commentToAdd, Task taskToAddComment);
        // 5. Added method in Members TaskManagementSystemRepositoryInterface getMemberById();

        Task task = getTaskManagementSystemRepository().findTaskByID(taskIndex);

        ValidationHelpers.validateIntRange(taskIndex, 0,task.getAllTasks().size() - 1, TASK_DOES_NOT_EXIST);
        Comment comment = getTaskManagementSystemRepository().createComment(content,author);

        getTaskManagementSystemRepository().getMemberById().addComment(comment, task);
        //ToDo(Done) - Dinko
        // 1. Added method String getUsername() in Members Interface
        return String.format(COMMENT_ADDED_SUCCESSFULLY, getTaskManagementSystemRepository().getMemberById().getUsername());
    }
}


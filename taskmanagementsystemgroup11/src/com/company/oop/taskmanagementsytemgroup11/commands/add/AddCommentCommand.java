package com.company.oop.taskmanagementsytemgroup11.commands.add;

import com.company.oop.taskmanagementsytemgroup11.commands.BaseCommand;
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

    public AddCommentCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String content = parameters.get(0);
        String author = parameters.get(1);
        int taskIndex = ParsingHelpers.tryParseInteger(parameters.get(2), INVALID_INPUT_MESSAGE);
        return addComment(content, author, taskIndex);
    }

    private String addComment(String content, String author, int taskIndex) {
        Task task = getTaskManagementSystemRepository().findTaskByID(taskIndex);
        Comment comment = getTaskManagementSystemRepository().createComment(content, author);
        getTaskManagementSystemRepository().getMemberByUsername(author).addComment(comment, task);

        return String.format(COMMENT_ADDED_SUCCESSFULLY, getTaskManagementSystemRepository().getMemberByUsername(author).getUsername());
    }
}


package com.company.oop.taskmanagementsystemgroup11.tests.commands;

import com.company.oop.taskmanagementsystemgroup11.tests.models.CommentImplTests;
import com.company.oop.taskmanagementsystemgroup11.tests.models.StoryImplTest;
import com.company.oop.taskmanagementsystemgroup11.tests.utils.TaskConstants;
import com.company.oop.taskmanagementsystemgroup11.tests.utils.TestUtilities;
import com.company.oop.taskmanagementsytemgroup11.commands.add.AddCommentCommand;
import com.company.oop.taskmanagementsytemgroup11.commands.contracts.Command;
import com.company.oop.taskmanagementsytemgroup11.core.TaskManagementSystemRepositoryImpl;
import com.company.oop.taskmanagementsytemgroup11.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsytemgroup11.models.StoryImpl;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Comment;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Story;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Task;
import com.company.oop.taskmanagementsytemgroup11.models.enums.Priority;
import com.company.oop.taskmanagementsytemgroup11.models.enums.Size;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class AddCommentTest {

    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 3;
    List<String> parameters;
    private TaskManagementSystemRepository taskManagementSystemRepository;
    private Command addCommentCommand;

    @BeforeEach
    public void initializeCommandAndRepository() {
        taskManagementSystemRepository = new TaskManagementSystemRepositoryImpl();
        addCommentCommand = new AddCommentCommand(taskManagementSystemRepository);
    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        //Arrange
        List<String> parameters = TestUtilities.getList(EXPECTED_NUMBER_OF_ARGUMENTS - 1);
        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> addCommentCommand.execute(parameters));
    }

    //ToDo
    // Doesn't find the index of the story
    @Test
    public void should_AddComment_When_InputIsValid() {
        //Arrange
        Comment testComment = taskManagementSystemRepository.createComment
                ("validContent", "Dinko_Kovachev1");
        Story story = taskManagementSystemRepository.createStory(TestUtilities.getString(TaskConstants.VALID_TITLE),
                TestUtilities.getString(TaskConstants.VALID_DESCRIPTION), Priority.HIGH, Size.LARGE,
                TestUtilities.getString(TaskConstants.VALID_USERNAME), 1,
                TestUtilities.getString(TaskConstants.VALID_TEAM_NAME),
                TestUtilities.getString(TaskConstants.VALID_BOARD_NAME));

        List<String> parameters = List.of(testComment.getContent(), testComment.getAuthor(),
                String.valueOf(taskManagementSystemRepository.findTaskByID(1).getId()));
        //Act
        addCommentCommand.execute(parameters);

    }

}

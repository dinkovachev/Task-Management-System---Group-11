package com.company.oop.taskmanagementsystemgroup11.tests.commands;

import com.company.oop.taskmanagementsystemgroup11.tests.models.CommentImplTests;
import com.company.oop.taskmanagementsystemgroup11.tests.models.StoryImplTest;
import com.company.oop.taskmanagementsystemgroup11.tests.utils.TestUtilities;
import com.company.oop.taskmanagementsytemgroup11.commands.add.AddCommentCommand;
import com.company.oop.taskmanagementsytemgroup11.commands.contracts.Command;
import com.company.oop.taskmanagementsytemgroup11.core.TaskManagementSystemRepositoryImpl;
import com.company.oop.taskmanagementsytemgroup11.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsytemgroup11.models.StoryImpl;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Comment;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Story;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Task;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class AddCommentTest {

    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 3;

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
    public void should_Create_When_InputIsValid() {
        //Arrange
        Comment testComment = taskManagementSystemRepository.createComment
                ("validContent", "Dinko_Kovachev1");
        Story story = new StoryImpl(StoryImplTest.VALID_ID, StoryImplTest.VALID_TITLE, StoryImplTest.VALID_DESCRIPTION,
                StoryImplTest.VALID_PRIORITY, StoryImplTest.VALID_SIZE, StoryImplTest.VALID_USERNAME,
                StoryImplTest.VALID_TASK_INDEX, StoryImplTest.VALID_BOARD_NAME, StoryImplTest.VALID_TEAM_NAME);
        List<String> parameters = List.of(CommentImplTests.VALID_CONTENT, CommentImplTests.VALID_AUTHOR,
                String.valueOf(taskManagementSystemRepository.findTaskByID(story.getTaskIndex())));
        //Act
        addCommentCommand.execute(parameters);
    }

}

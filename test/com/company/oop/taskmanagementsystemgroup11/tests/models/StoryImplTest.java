package com.company.oop.taskmanagementsystemgroup11.tests.models;

import com.company.oop.taskmanagementsystemgroup11.tests.utils.TaskConstants;
import com.company.oop.taskmanagementsystemgroup11.tests.utils.TestUtilities;
import com.company.oop.taskmanagementsytemgroup11.commands.contracts.Command;
import com.company.oop.taskmanagementsytemgroup11.core.TaskManagementSystemRepositoryImpl;
import com.company.oop.taskmanagementsytemgroup11.models.StoryImpl;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Story;
import com.company.oop.taskmanagementsytemgroup11.models.enums.Priority;
import com.company.oop.taskmanagementsytemgroup11.models.enums.Size;
import com.company.oop.taskmanagementsytemgroup11.models.enums.Status;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class StoryImplTest {

    public static final Priority VALID_PRIORITY = Priority.HIGH;
    public static final Size VALID_SIZE = Size.MEDIUM;

    private TaskManagementSystemRepositoryImpl repository;
    private Command changeSizeCommand;
    private List<String> params;


    public static StoryImpl initializeStory() {
        return new StoryImpl(
                TaskConstants.VALID_ID,
                TestUtilities.getString(TaskConstants.VALID_TITLE),
                TestUtilities.getString(TaskConstants.VALID_DESCRIPTION),
                VALID_PRIORITY,
                VALID_SIZE,
                TestUtilities.getString(TaskConstants.VALID_USERNAME),
                TaskConstants.VALID_ID,
                TestUtilities.getString(TaskConstants.VALID_TEAM_NAME),
                TestUtilities.getString(TaskConstants.VALID_BOARD_NAME));
    }

    @Test
    public void storyImpl_Should_ImplementStoryInterface() {
        // Arrange, Act
        StoryImpl story = initializeStory();
        // Assert
        Assertions.assertTrue(story instanceof Story);
    }

    @Test
    public void constructor_Should_ThrowException_When_TitleLengthOutOfBounds() {
        // Arrange, Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new StoryImpl(
                        TaskConstants.VALID_ID,
                        TestUtilities.getString(TaskConstants.VALID_TITLE - 1),
                        TestUtilities.getString(TaskConstants.VALID_DESCRIPTION),
                        VALID_PRIORITY,
                        VALID_SIZE,
                        TestUtilities.getString(TaskConstants.VALID_USERNAME),
                        TaskConstants.VALID_ID,
                        TestUtilities.getString(TaskConstants.VALID_TEAM_NAME),
                        TestUtilities.getString(TaskConstants.VALID_BOARD_NAME)));
    }

    @Test
    public void advanceStatus_Should_advanceStatusToInProgress_When_StoryStatusIsNotDone() {
        // Arrange
        StoryImpl story = initializeStory();
        // Act
        story.advanceStatus();
        // Assert
        Assertions.assertEquals(Status.IN_PROGRESS, story.getStatus());
    }

    @Test
    public void revertStatus_Should_revertStatusToInProgress_When_StoryStatusIsDone() {
        // Arrange
        StoryImpl story = initializeStory();
        story.advanceStatus();
        story.advanceStatus();
        // Act
        story.revertStatus();
        // Assert
        Assertions.assertEquals(Status.IN_PROGRESS, story.getStatus());
    }

    @Test
    public void revertStatus_Should_revertStatusToNotDone_When_StoryStatusIsNotDone() {
        // Arrange
        StoryImpl story = initializeStory();
        // Act
        story.revertStatus();
        // Assert
        Assertions.assertEquals(Status.NOT_DONE, story.getStatus());
    }

    @Test
    public void revertStatus_Should_revertStatusToNotDone_When_StoryStatusIsInProgress() {
        // Arrange
        StoryImpl story = initializeStory();
        story.advanceStatus();
        // Act
        story.revertStatus();
        // Assert
        Assertions.assertEquals(Status.NOT_DONE, story.getStatus());
    }

    @Test
    public void changePriority_Should_ChangePriority_When_ValidPriorityIsPassed() {
        // Arrange
        StoryImpl story = initializeStory();
        // Act
        story.changePriority(TaskConstants.VALID_ID, Priority.LOW);
        // Assert
        Assertions.assertEquals(Priority.LOW, story.getPriority());
    }

    @Test
    public void changeSize_Should_ChangeSize_When_ValidSizeIsPassed() {
        // Arrange
        StoryImpl story = initializeStory();
        // Act
        story.changeSize(TaskConstants.VALID_ID, Size.SMALL);
        // Assert
        Assertions.assertEquals(Size.SMALL, story.getSize());
    }
}


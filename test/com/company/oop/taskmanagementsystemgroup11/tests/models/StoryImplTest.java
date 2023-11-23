package com.company.oop.taskmanagementsystemgroup11.tests.models;

import com.company.oop.taskmanagementsystemgroup11.tests.utils.TestUtilities;
import com.company.oop.taskmanagementsytemgroup11.models.StoryImpl;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Story;
import com.company.oop.taskmanagementsytemgroup11.models.enums.Priority;
import com.company.oop.taskmanagementsytemgroup11.models.enums.Size;
import com.company.oop.taskmanagementsytemgroup11.models.enums.Status;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StoryImplTest {

    private static final int VALID_ID = 1;
    private static final String VALID_TITLE = TestUtilities.getString(10);
    private static final String VALID_DESCRIPTION = TestUtilities.getString(10);
    private static final Priority VALID_PRIORITY = Priority.HIGH;
    private static final Size VALID_SIZE = Size.MEDIUM;
    private static final String VALID_USERNAME = "Ilarion_Makariopolski1";
    private static final String VALID_TEAM_NAME = "TeamCherry";
    private static final String VALID_BOARD_NAME = "Whiteboard";

    public static StoryImpl initializeStory() {
        return new StoryImpl(VALID_ID, VALID_TITLE, VALID_DESCRIPTION, VALID_PRIORITY, VALID_SIZE, VALID_USERNAME,
                VALID_ID, VALID_TEAM_NAME, VALID_BOARD_NAME);
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
                        VALID_ID,
                        TestUtilities.getString(VALID_TITLE.length() - 1),
                        VALID_DESCRIPTION,
                        VALID_PRIORITY,
                        VALID_SIZE,
                        VALID_USERNAME,
                        VALID_ID,
                        VALID_TEAM_NAME,
                        VALID_BOARD_NAME));
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
        story.changePriority(VALID_ID, Priority.LOW);
        // Assert
        Assertions.assertEquals(Priority.LOW, story.getPriority());
    }

    @Test
    public void changeSize_Should_ChangeSize_When_ValidSizeIsPassed() {
        // Arrange
        StoryImpl story = initializeStory();
        // Act
        story.changeSize(VALID_ID, Size.SMALL);
        // Assert
        Assertions.assertEquals(Size.SMALL, story.getSize());
    }
}


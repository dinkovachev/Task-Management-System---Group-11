package com.company.oop.taskmanagementsystemgroup11.tests.models;

import com.company.oop.taskmanagementsystemgroup11.tests.utils.TestUtilities;
import com.company.oop.taskmanagementsytemgroup11.models.FeedbackImpl;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Feedback;
import com.company.oop.taskmanagementsytemgroup11.models.enums.Status;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FeedbackImplTest {
    private static final int VALID_ID = 1;
    private static final String VALID_TITLE = TestUtilities.getString(10);
    private static final String VALID_DESCRIPTION = TestUtilities.getString(10);
    private static final int VALID_RATING = 1;
    private static final String VALID_USERNAME = "Ilarion_Makariopolski1";
    private static final String VALID_TEAM_NAME = "TeamCherry";
    private static final String VALID_BOARD_NAME = "Whiteboard";

    public static FeedbackImpl initializeFeedback() {
        return new FeedbackImpl(VALID_ID, VALID_TITLE, VALID_DESCRIPTION, VALID_RATING, VALID_TEAM_NAME,
                VALID_BOARD_NAME);
    }

    @Test
    public void feedbackImpl_Should_ImplementFeedbackInterface() {
        // Arrange, Act
        FeedbackImpl feedback = initializeFeedback();
        // Assert
        Assertions.assertTrue(feedback instanceof Feedback);
    }

    @Test
    public void constructor_Should_ThrowException_When_TitleLengthOutOfBounds() {
        // Arrange, Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new FeedbackImpl(
                        VALID_ID,
                        TestUtilities.getString(VALID_TITLE.length() - 1),
                        VALID_DESCRIPTION,
                        VALID_RATING,
                        VALID_TEAM_NAME,
                        VALID_BOARD_NAME));
    }

    @Test
    public void constructor_Should_ThrowException_When_DescriptionLengthOutOfBounds() {
        // Arrange, Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new FeedbackImpl(
                        VALID_ID,
                        VALID_TITLE,
                        TestUtilities.getString(VALID_DESCRIPTION.length() - 1),
                        VALID_RATING,
                        VALID_TEAM_NAME,
                        VALID_BOARD_NAME));
    }

    @Test
    public void constructor_Should_ThrowException_When_RatingOutOfBounds() {
        // Arrange, Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new FeedbackImpl(
                        VALID_ID,
                        VALID_TITLE,
                        VALID_DESCRIPTION,
                        0,
                        VALID_TEAM_NAME,
                        VALID_BOARD_NAME));
    }

    @Test
    public void advanceStatus_Should_advanceStatusToDone_When_feedbackStatusIsNew() {
        // Arrange
        FeedbackImpl feedback = initializeFeedback();
        // Act
        feedback.advanceStatus();
        feedback.advanceStatus();
        feedback.advanceStatus();
        // Assert
        Assertions.assertEquals(Status.DONE, feedback.getStatus());
    }

    @Test
    public void revertStatus_Should_revertStatusToNew_When_feedbackStatusIsNew() {
        // Arrange
        FeedbackImpl feedback = initializeFeedback();
        // Act
        feedback.revertStatus();
        // Assert
        Assertions.assertEquals(Status.NEW, feedback.getStatus());
            }

    @Test
    public void revertStatus_Should_revertStatusToNew_When_feedbackStatusIsDone() {
        // Arrange
        FeedbackImpl feedback = initializeFeedback();
        // Act
        feedback.advanceStatus();
        feedback.advanceStatus();
        feedback.advanceStatus();
        feedback.revertStatus();
        feedback.revertStatus();
        feedback.revertStatus();
        // Assert
        Assertions.assertEquals(Status.NEW, feedback.getStatus());
    }

    @Test
    public void changeRating_Should_ChangeRating_When_ValidRatingIsPassed() {
        // Arrange
        FeedbackImpl feedback = initializeFeedback();
        // Act
        feedback.changeRating(VALID_ID, 10);
        // Assert
        Assertions.assertEquals(10, feedback.getRating());
    }
}

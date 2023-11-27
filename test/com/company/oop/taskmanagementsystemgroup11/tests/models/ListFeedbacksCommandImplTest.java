package com.company.oop.taskmanagementsystemgroup11.tests.models;

import com.company.oop.taskmanagementsystemgroup11.tests.utils.TaskConstants;
import com.company.oop.taskmanagementsystemgroup11.tests.utils.TestUtilities;
import com.company.oop.taskmanagementsytemgroup11.models.FeedbackImpl;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Feedback;
import com.company.oop.taskmanagementsytemgroup11.models.enums.Status;



import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ListFeedbacksCommandImplTest {

    public static FeedbackImpl initializeFeedback() {
        return new FeedbackImpl(
                TaskConstants.VALID_ID,
                TestUtilities.getString(TaskConstants.VALID_TITLE),
                TestUtilities.getString(TaskConstants.VALID_DESCRIPTION),
                TaskConstants.VALID_RATING,
                TestUtilities.getString(TaskConstants.VALID_TEAM_NAME),
                TestUtilities.getString(TaskConstants.VALID_BOARD_NAME));
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
                        TaskConstants.VALID_ID,
                        TestUtilities.getString(TaskConstants.VALID_TITLE - 1),
                        TestUtilities.getString(TaskConstants.VALID_DESCRIPTION),
                        TaskConstants.VALID_RATING,
                        TestUtilities.getString(TaskConstants.VALID_TEAM_NAME),
                        TestUtilities.getString(TaskConstants.VALID_BOARD_NAME)));
    }

    @Test
    public void constructor_Should_ThrowException_When_DescriptionLengthOutOfBounds() {
        // Arrange, Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new FeedbackImpl(
                        TaskConstants.VALID_ID,
                        TestUtilities.getString(TaskConstants.VALID_TITLE),
                        TestUtilities.getString(TaskConstants.VALID_DESCRIPTION - 1),
                        TaskConstants.VALID_RATING,
                        TestUtilities.getString(TaskConstants.VALID_TEAM_NAME),
                        TestUtilities.getString(TaskConstants.VALID_BOARD_NAME)));
    }

    @Test
    public void constructor_Should_ThrowException_When_RatingOutOfBounds() {
        // Arrange, Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new FeedbackImpl(
                        TaskConstants.VALID_ID,
                        TestUtilities.getString(TaskConstants.VALID_TITLE),
                        TestUtilities.getString(TaskConstants.VALID_DESCRIPTION),
                        TaskConstants.VALID_RATING - 1,
                        TestUtilities.getString(TaskConstants.VALID_TEAM_NAME),
                        TestUtilities.getString(TaskConstants.VALID_BOARD_NAME)));
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
        feedback.changeRating(TaskConstants.VALID_ID, 10);
        // Assert
        Assertions.assertEquals(10, feedback.getRating());
    }
}

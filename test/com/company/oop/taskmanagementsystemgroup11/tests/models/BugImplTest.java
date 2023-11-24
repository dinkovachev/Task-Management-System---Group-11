package com.company.oop.taskmanagementsystemgroup11.tests.models;

import com.company.oop.taskmanagementsystemgroup11.tests.utils.TaskConstants;
import com.company.oop.taskmanagementsystemgroup11.tests.utils.TestUtilities;
import com.company.oop.taskmanagementsytemgroup11.models.BugImpl;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Bug;
import com.company.oop.taskmanagementsytemgroup11.models.enums.Priority;
import com.company.oop.taskmanagementsytemgroup11.models.enums.Severity;
import com.company.oop.taskmanagementsytemgroup11.models.enums.Status;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BugImplTest {
    private static final Priority VALID_PRIORITY = Priority.HIGH;
    private static final Severity VALID_SEVERITY = Severity.MAJOR;
    private static final String VALID_STEPS_TO_REPRODUCE = "1.TurnOnThePC;2.GetAHammer;3.SmackIt;";

    public static BugImpl initializeBug() {
        return new BugImpl(
                TaskConstants.VALID_ID,
                TestUtilities.getString(TaskConstants.VALID_TITLE),
                TestUtilities.getString(TaskConstants.VALID_DESCRIPTION),
                VALID_STEPS_TO_REPRODUCE,
                VALID_PRIORITY,
                VALID_SEVERITY,
                TestUtilities.getString(TaskConstants.VALID_USERNAME),
                TaskConstants.VALID_ID,
                TestUtilities.getString(TaskConstants.VALID_TEAM_NAME),
                TestUtilities.getString(TaskConstants.VALID_BOARD_NAME));
    }

    @Test
    public void bugImpl_Should_ImplementBugInterface() {
        // Arrange, Act
        BugImpl bug = initializeBug();
        // Assert
        Assertions.assertTrue(bug instanceof Bug);
    }

    @Test
    public void constructor_Should_ThrowException_When_TitleLengthOutOfBounds() {
        // Arrange, Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new BugImpl(
                        TaskConstants.VALID_ID,
                        TestUtilities.getString(TaskConstants.VALID_TITLE - 1),
                        TestUtilities.getString(TaskConstants.VALID_DESCRIPTION),
                        VALID_STEPS_TO_REPRODUCE,
                        VALID_PRIORITY,
                        VALID_SEVERITY,
                        TestUtilities.getString(TaskConstants.VALID_USERNAME),
                        TaskConstants.VALID_ID,
                        TestUtilities.getString(TaskConstants.VALID_TEAM_NAME),
                        TestUtilities.getString(TaskConstants.VALID_BOARD_NAME)));
    }

    @Test
    public void constructor_Should_ThrowException_When_DescriptionLengthOutOfBounds() {
        // Arrange, Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new BugImpl(

                        TaskConstants.VALID_ID,
                        TestUtilities.getString(TaskConstants.VALID_TITLE),
                        TestUtilities.getString(TaskConstants.VALID_DESCRIPTION - 1),
                        VALID_STEPS_TO_REPRODUCE,
                        VALID_PRIORITY,
                        VALID_SEVERITY,
                        TestUtilities.getString(TaskConstants.VALID_USERNAME),
                        TaskConstants.VALID_ID,
                        TestUtilities.getString(TaskConstants.VALID_TEAM_NAME),
                        TestUtilities.getString(TaskConstants.VALID_BOARD_NAME)));
    }

    @Test
    public void changePriority_Should_ChangePriority_When_ValidPriorityIsPassed() {
        // Arrange
        BugImpl bug = initializeBug();
        // Act
        bug.changePriority(TaskConstants.VALID_ID, Priority.LOW);
        // Assert
        Assertions.assertEquals(Priority.LOW, bug.getPriority());
    }

    @Test
    public void changeSeverity_Should_ChangeSeverity_When_ValidSeverityIsPassed() {
        // Arrange
        BugImpl bug = initializeBug();
        // Act
        bug.changeSeverity(TaskConstants.VALID_ID, Severity.MINOR);
        // Assert
        Assertions.assertEquals(Severity.MINOR, bug.getSeverity());
    }

    @Test
    public void advanceStatus_Should_advanceStatusToDone_When_BugStatusIsActive() {
        // Arrange
        BugImpl bug = initializeBug();
        // Act
        bug.advanceStatus();
        // Assert
        Assertions.assertEquals(Status.DONE, bug.getStatus());
    }

    @Test
    public void revertStatus_Should_revertStatusToActive_When_BugStatusIsDone() {
        // Arrange
        BugImpl bug = initializeBug();
        bug.advanceStatus();
        bug.advanceStatus();
        // Act
        bug.revertStatus();
        // Assert
        Assertions.assertEquals(Status.ACTIVE, bug.getStatus());
    }

    @Test
    public void revertStatus_Should_revertStatusToActive_When_BugStatusIsActive() {
        // Arrange
        BugImpl bug = initializeBug();
        bug.revertStatus();
        // Act
        bug.revertStatus();
        // Assert
        Assertions.assertEquals(Status.ACTIVE, bug.getStatus());
    }
}
package com.company.oop.taskmanagementsystemgroup11.tests.commands;

import com.company.oop.taskmanagementsystemgroup11.tests.utils.TaskConstants;
import com.company.oop.taskmanagementsystemgroup11.tests.utils.TestUtilities;
import com.company.oop.taskmanagementsytemgroup11.commands.change.ChangeStatusCommand;
import com.company.oop.taskmanagementsytemgroup11.commands.contracts.Command;
import com.company.oop.taskmanagementsytemgroup11.core.TaskManagementSystemRepositoryImpl;
import com.company.oop.taskmanagementsytemgroup11.models.enums.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.company.oop.taskmanagementsystemgroup11.tests.models.StoryImplTest.VALID_PRIORITY;

public class ChangeStatusTest {

    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 3;
    private static final int STEPS_TO_REPRODUCE = 15;
    private TaskManagementSystemRepositoryImpl repository;
    private Command changeStatusCommand;
    private List<String> params;

    @BeforeEach
    public void before() {
        params = new ArrayList<>();
        repository = new TaskManagementSystemRepositoryImpl();
        changeStatusCommand = new ChangeStatusCommand(repository);
    }

    @BeforeEach
    public void beforeTasks() {


        repository.createStory(
                TestUtilities.getString(TaskConstants.VALID_TITLE),
                TestUtilities.getString(TaskConstants.VALID_DESCRIPTION),
                VALID_PRIORITY,
                Size.LARGE,
                TestUtilities.getString(TaskConstants.VALID_USERNAME),
                TestUtilities.getString(TaskConstants.VALID_TEAM_NAME),
                TestUtilities.getString(TaskConstants.VALID_BOARD_NAME));

        repository.createBug(
                TestUtilities.getString(TaskConstants.VALID_TITLE),
                TestUtilities.getString(TaskConstants.VALID_DESCRIPTION),
                TestUtilities.getString(STEPS_TO_REPRODUCE),
                Priority.MEDIUM,
                Severity.MINOR,
                TestUtilities.getString(TaskConstants.VALID_USERNAME),
                TaskConstants.VALID_ID,
                TestUtilities.getString(TaskConstants.VALID_TEAM_NAME),
                TestUtilities.getString(TaskConstants.VALID_BOARD_NAME));

        repository.createFeedback(
                TestUtilities.getString(TaskConstants.VALID_TITLE),
                TestUtilities.getString(TaskConstants.VALID_DESCRIPTION),
                TaskConstants.VALID_RATING,
                TaskConstants.VALID_ID,
                TestUtilities.getString(TaskConstants.VALID_TEAM_NAME),
                TestUtilities.getString(TaskConstants.VALID_BOARD_NAME));
    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        // Arrange
        List<String> params = TestUtilities.getList(EXPECTED_NUMBER_OF_ARGUMENTS - 1);

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> changeStatusCommand.execute(params));
    }

    @Test
    public void should_AdvanceStoryStatus_When_ArgumentsAreValid() {
        // Arrange
        params = List.of(
                TaskType.STORY.toString(),
                "advance",
                "1");

        // Act
        changeStatusCommand.execute(params);

        // Assert
        Assertions.assertEquals(Status.IN_PROGRESS, repository.findStoryByTaskIndex(1).getStatus());
    }

    @Test
    public void should_RevertStoryStatus_When_ArgumentsAreValid() {
        // Arrange
        params = List.of(
                TaskType.STORY.toString(),
                "revert",
                "1");

        // Act
        changeStatusCommand.execute(params);

        // Assert
        Assertions.assertEquals(Status.NOT_DONE, repository.findStoryByTaskIndex(1).getStatus());
    }

    @Test
    public void should_AdvanceBugStatus_When_ArgumentsAreValid() {
        // Arrange
        params = List.of(
                TaskType.BUG.toString(),
                "advance",
                "2");

        // Act
        changeStatusCommand.execute(params);

        // Assert
        Assertions.assertEquals(Status.DONE, repository.findBugByTaskIndex(2).getStatus());
    }

    @Test
    public void should_RevertBugStatus_When_ArgumentsAreValid() {
        // Arrange
        params = List.of(
                TaskType.BUG.toString(),
                "revert",
                "2");

        // Act
        changeStatusCommand.execute(params);

        // Assert
        Assertions.assertEquals(Status.ACTIVE, repository.findBugByTaskIndex(2).getStatus());
    }

    @Test
    public void should_AdvanceFeedbackStatus_When_ArgumentsAreValid() {
        // Arrange
        params = List.of(
                TaskType.FEEDBACK.toString(),
                "advance",
                "3");

        // Act
        changeStatusCommand.execute(params);

        // Assert
        Assertions.assertEquals(Status.UNSCHEDULED, repository.findFeedbackByTaskIndex(3).getStatus());
    }

    @Test
    public void should_RevertFeedbackStatus_When_ArgumentsAreValid() {
        // Arrange
        params = List.of(
                TaskType.FEEDBACK.toString(),
                "revert",
                "3");

        // Act
        changeStatusCommand.execute(params);

        // Assert
        Assertions.assertEquals(Status.NEW, repository.findFeedbackByTaskIndex(3).getStatus());
    }
}
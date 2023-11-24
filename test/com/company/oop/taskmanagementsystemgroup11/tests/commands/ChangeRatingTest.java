package com.company.oop.taskmanagementsystemgroup11.tests.commands;

import com.company.oop.taskmanagementsystemgroup11.tests.utils.TaskConstants;
import com.company.oop.taskmanagementsystemgroup11.tests.utils.TestUtilities;
import com.company.oop.taskmanagementsytemgroup11.commands.change.ChangeRatingCommand;
import com.company.oop.taskmanagementsytemgroup11.commands.contracts.Command;
import com.company.oop.taskmanagementsytemgroup11.core.TaskManagementSystemRepositoryImpl;
import com.company.oop.taskmanagementsytemgroup11.models.enums.TaskType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ChangeRatingTest {
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 3;
    private TaskManagementSystemRepositoryImpl repository;
    private Command changeRatingCommand;
    private List<String> params;

    @BeforeEach
    public void before() {
        params = new ArrayList<>();
        repository = new TaskManagementSystemRepositoryImpl();
        changeRatingCommand = new ChangeRatingCommand(repository);
    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        // Arrange
        List<String> params = TestUtilities.getList(EXPECTED_NUMBER_OF_ARGUMENTS - 1);

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> changeRatingCommand.execute(params));
    }

    @Test
    public void should_ChangeRating_When_ArgumentsAreValid() {
        // Arrange
        repository.createFeedback(
                TestUtilities.getString(TaskConstants.VALID_TITLE),
                TestUtilities.getString(TaskConstants.VALID_DESCRIPTION),
                TaskConstants.VALID_RATING,
                TaskConstants.VALID_ID,
                TestUtilities.getString(TaskConstants.VALID_TEAM_NAME),
                TestUtilities.getString(TaskConstants.VALID_BOARD_NAME));

        params = List.of(
                TaskType.FEEDBACK.toString(),
                "7",
                "1");

        // Act
        changeRatingCommand.execute(params);

        // Assert
        Assertions.assertEquals(7, repository.findFeedbackByTaskIndex(1).getRating());
    }
}

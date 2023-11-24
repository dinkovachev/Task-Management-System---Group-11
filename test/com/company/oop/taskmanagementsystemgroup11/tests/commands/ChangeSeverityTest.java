package com.company.oop.taskmanagementsystemgroup11.tests.commands;

import com.company.oop.taskmanagementsystemgroup11.tests.utils.TaskConstants;
import com.company.oop.taskmanagementsystemgroup11.tests.utils.TestUtilities;
import com.company.oop.taskmanagementsytemgroup11.commands.change.ChangeSeverityCommand;
import com.company.oop.taskmanagementsytemgroup11.commands.contracts.Command;
import com.company.oop.taskmanagementsytemgroup11.core.TaskManagementSystemRepositoryImpl;
import com.company.oop.taskmanagementsytemgroup11.models.enums.Priority;
import com.company.oop.taskmanagementsytemgroup11.models.enums.Severity;
import com.company.oop.taskmanagementsytemgroup11.models.enums.TaskType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ChangeSeverityTest {
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 3;
    private static final int STEPS_TO_REPRODUCE = 15;
    private TaskManagementSystemRepositoryImpl repository;
    private Command changeSeverityCommand;
    private List<String> params;

    @BeforeEach
    public void before() {
        params = new ArrayList<>();
        repository = new TaskManagementSystemRepositoryImpl();
        changeSeverityCommand = new ChangeSeverityCommand(repository);
    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        // Arrange
        List<String> params = TestUtilities.getList(EXPECTED_NUMBER_OF_ARGUMENTS - 1);

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> changeSeverityCommand.execute(params));
    }

    @Test
    public void should_ChangeBugSeverity_When_ArgumentsAreValid() {
        // Arrange
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

        params = List.of(
                TaskType.BUG.toString(),
                Severity.CRITICAL.toString(),
                "1");

        // Act
        changeSeverityCommand.execute(params);

        // Assert
        Assertions.assertEquals(Severity.CRITICAL, repository.findBugByTaskIndex(1).getSeverity());
    }
}

package com.company.oop.taskmanagementsystemgroup11.tests.commands;

import com.company.oop.taskmanagementsystemgroup11.tests.utils.TestUtilities;
import com.company.oop.taskmanagementsytemgroup11.commands.contracts.Command;
import com.company.oop.taskmanagementsytemgroup11.commands.creation.CreateFeedbackCommand;
import com.company.oop.taskmanagementsytemgroup11.core.TaskManagementSystemRepositoryImpl;
import com.company.oop.taskmanagementsytemgroup11.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Board;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Team;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class CreateFeedbackTest {
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 5;

    private List<String> params;
    private TaskManagementSystemRepository repository;
    private Command createFeedbackCommand;

    @BeforeEach
    public void before() {
        params = new ArrayList<>();
        repository = new TaskManagementSystemRepositoryImpl();
        createFeedbackCommand = new CreateFeedbackCommand(repository);
    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        // Arrange
        params = TestUtilities.getList(EXPECTED_NUMBER_OF_ARGUMENTS - 1);

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> createFeedbackCommand.execute(params));
    }

    @Test
    public void should_CreateFeedback_When_ArgumentsAreValid() {
        Team team = repository.createTeam("teamName");
        Board board = repository.createBoard("boardName");
        params = List.of(
                "feedbackName",
                "feedbackDescription",
                "7",
                team.getName(),
                board.getName());

        // Act,
        createFeedbackCommand.execute(params);

        // Assert
        Assertions.assertAll(
                () -> Assertions.assertEquals(
                        "feedbackName", repository.findFeedbackByTaskIndex(1).getTitle()),
                () -> Assertions.assertEquals(
                        "feedbackDescription", repository.findFeedbackByTaskIndex(1).getDescription()),
                () -> Assertions.assertEquals(
                        1, repository.findFeedbackByTaskIndex(1).getId()));
    }
}

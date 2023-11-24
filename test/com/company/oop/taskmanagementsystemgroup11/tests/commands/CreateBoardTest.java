package com.company.oop.taskmanagementsystemgroup11.tests.commands;

import com.company.oop.taskmanagementsystemgroup11.tests.utils.TestUtilities;
import com.company.oop.taskmanagementsytemgroup11.commands.contracts.Command;
import com.company.oop.taskmanagementsytemgroup11.commands.creation.CreateBoardCommand;
import com.company.oop.taskmanagementsytemgroup11.core.TaskManagementSystemRepositoryImpl;
import com.company.oop.taskmanagementsytemgroup11.models.BoardImpl;
import com.company.oop.taskmanagementsytemgroup11.models.TeamImpl;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Board;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Team;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class CreateBoardTest {

    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    private TaskManagementSystemRepositoryImpl repository;
    private Command createBoardCommand;
    private Command createTeamCommand;
    private List<String> paramsTeam;
    private List<String> paramsBoard;

    @BeforeEach
    public void before() {
        paramsBoard = new ArrayList<>();
        repository = new TaskManagementSystemRepositoryImpl();
        createBoardCommand = new CreateBoardCommand(repository);
    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        // Arrange
        List<String> params = TestUtilities.getList(EXPECTED_NUMBER_OF_ARGUMENTS - 1);

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> createBoardCommand.execute(params));
    }

    @Test
    public void should_CreateBoard_When_ArgumentsAreValid() {

        paramsTeam = List.of(
                "teamName");

        createTeamCommand.execute(paramsTeam);

        paramsBoard = List.of(
                "boardName",
                "teamName");

        // Act,
        createBoardCommand.execute(paramsBoard);

        // Assert
        Assertions.assertEquals(
                "boardName", repository.getBoardByName("boardName").getName());
    }
}

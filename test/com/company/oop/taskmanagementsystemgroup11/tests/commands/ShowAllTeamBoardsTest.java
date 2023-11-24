package com.company.oop.taskmanagementsystemgroup11.tests.commands;

import com.company.oop.taskmanagementsystemgroup11.tests.utils.TestUtilities;
import com.company.oop.taskmanagementsytemgroup11.commands.show.ShowAllTeamBoardsCommand;
import com.company.oop.taskmanagementsytemgroup11.core.TaskManagementSystemRepositoryImpl;
import com.company.oop.taskmanagementsytemgroup11.core.contracts.TaskManagementSystemRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.List;

public class ShowAllTeamBoardsTest {

    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    public static final String VALID_TEAM_NAME = "alabala";

    private ShowAllTeamBoardsCommand showAllTeamBoardsCommand;
    private TaskManagementSystemRepository taskManagementSystemRepository;

    @BeforeEach
    public void initializeCommandAndRepository() {
        taskManagementSystemRepository = new TaskManagementSystemRepositoryImpl();
        showAllTeamBoardsCommand = new ShowAllTeamBoardsCommand(taskManagementSystemRepository);
    }

    @Test
    public void should_Throw_Exception_When_ArgumentCountDifferent() {
        //Arrange
        List<String> parameters = TestUtilities.getList(EXPECTED_NUMBER_OF_ARGUMENTS - 1);
        //Act,Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> showAllTeamBoardsCommand.execute(parameters));
    }

    @Test
    public void should_ShowNoTeamBoards_In_TheTeam_WhenListIsEmpty() {
        //Arrange
        taskManagementSystemRepository.createTeam(VALID_TEAM_NAME);
        List<String> parameters = List.of(VALID_TEAM_NAME);
        // Act, Assert
        Assertions.assertDoesNotThrow(() -> showAllTeamBoardsCommand.execute(parameters));
    }

    @Test
    public void should_ShowAllTeamBoards_In_TheTeam() {
        //Arrange,
        taskManagementSystemRepository.createTeam(VALID_TEAM_NAME);
        List<String> parameters = List.of(VALID_TEAM_NAME);

        taskManagementSystemRepository.getAllTeamsBoards();
        //Act, Assert
        Assertions.assertDoesNotThrow(() -> showAllTeamBoardsCommand.execute(parameters));
    }
}

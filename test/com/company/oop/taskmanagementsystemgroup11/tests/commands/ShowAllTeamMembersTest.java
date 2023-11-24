package com.company.oop.taskmanagementsystemgroup11.tests.commands;

import com.company.oop.taskmanagementsystemgroup11.tests.utils.TestUtilities;
import com.company.oop.taskmanagementsytemgroup11.commands.show.ShowAllTeamMembersCommand;
import com.company.oop.taskmanagementsytemgroup11.core.TaskManagementSystemRepositoryImpl;
import com.company.oop.taskmanagementsytemgroup11.core.contracts.TaskManagementSystemRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ShowAllTeamMembersTest {
    private static final int EXPECTED_NUMBER_OF_PARAMETERS = 1;
    public static final String VALID_TEAM_NAME = "alabala";

    private ShowAllTeamMembersCommand showAllTeamMembersCommand;
    private TaskManagementSystemRepository taskManagementSystemRepository;

    @BeforeEach
    public void initializeCommandAndRepository() {
        taskManagementSystemRepository = new TaskManagementSystemRepositoryImpl();
        showAllTeamMembersCommand = new ShowAllTeamMembersCommand(taskManagementSystemRepository);
    }

    @Test
    public void should_Throw_Exception_When_ArgumentsCountIsInvalid() {
        //Arrange
        List<String> parameters = TestUtilities.getList(EXPECTED_NUMBER_OF_PARAMETERS - 1);
        //Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> showAllTeamMembersCommand.execute(parameters));
    }

      @Test
    public void should_ShowNoTeamMembers_In_TheTeam_WhenListIsEmpty() {
        //Arrange
        taskManagementSystemRepository.createTeam(VALID_TEAM_NAME);
        List<String> parameters = List.of(VALID_TEAM_NAME);
        // Act, Assert
        Assertions.assertDoesNotThrow(() -> showAllTeamMembersCommand.execute(parameters));
    }


    @Test
    public void should_Show_All_Team_Members_When_ArgumentsAreValid(){
        //Arrange
        taskManagementSystemRepository.createTeam(VALID_TEAM_NAME);
        List<String> parameters = List.of((VALID_TEAM_NAME));
        taskManagementSystemRepository.getTeamByName(VALID_TEAM_NAME).getTeamMembers();
        //Act,Assert
        Assertions.assertDoesNotThrow(() -> showAllTeamMembersCommand.execute(parameters));
    }
}

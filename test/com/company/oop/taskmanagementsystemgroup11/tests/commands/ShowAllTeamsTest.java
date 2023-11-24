package com.company.oop.taskmanagementsystemgroup11.tests.commands;

import com.company.oop.taskmanagementsytemgroup11.commands.show.ShowAllTeamsCommand;
import com.company.oop.taskmanagementsytemgroup11.core.TaskManagementSystemRepositoryImpl;
import com.company.oop.taskmanagementsytemgroup11.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsytemgroup11.models.MembersImpl;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Members;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Team;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ShowAllTeamsTest {

    private ShowAllTeamsCommand showAllTeamsCommand;

    private TaskManagementSystemRepository taskManagementSystemRepository;

    private List<Team> teams = new ArrayList<>();

    //ToDo
    // Double check the part that is not covered

    @BeforeEach
    public void initializeCommandAndRepository(){
        taskManagementSystemRepository = new TaskManagementSystemRepositoryImpl();
        showAllTeamsCommand = new ShowAllTeamsCommand(taskManagementSystemRepository);

    }

    @Test
    public void should_ShowNoTeams_In_TheApplication_WhenListIsEmpty() {
        //Arrange, Act, Assert
        Assertions.assertDoesNotThrow(() -> showAllTeamsCommand.execute(List.of()));
    }
    @Test
    public void should_ShowAllTeams_In_TheApplication() {
        //Arrange
        Team testTeam = taskManagementSystemRepository.createTeam("alabala");
        teams.add(testTeam);
        teams = taskManagementSystemRepository.getAllTeams();
        // Act, Assert
        Assertions.assertDoesNotThrow(() -> showAllTeamsCommand.execute(List.of()));
    }
}

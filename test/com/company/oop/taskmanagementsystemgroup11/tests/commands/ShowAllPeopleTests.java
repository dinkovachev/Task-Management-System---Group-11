package com.company.oop.taskmanagementsystemgroup11.tests.commands;


import com.company.oop.taskmanagementsytemgroup11.commands.show.ShowAllPeopleCommand;
import com.company.oop.taskmanagementsytemgroup11.core.TaskManagementSystemRepositoryImpl;
import com.company.oop.taskmanagementsytemgroup11.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsytemgroup11.models.MembersImpl;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Members;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ShowAllPeopleTests {
    private ShowAllPeopleCommand showAllPeopleCommand;
    private TaskManagementSystemRepository taskManagementSystemRepository;

    List<Members> membersList = new ArrayList<>();

    // ToDo
    //Double check the part that is not covered
    @BeforeEach
    public void initializeCommandAndRepository() {
        taskManagementSystemRepository = new TaskManagementSystemRepositoryImpl();
        showAllPeopleCommand = new ShowAllPeopleCommand(taskManagementSystemRepository);

    }

    @Test
    public void should_ShowNoPeople_In_TheApplication_WhenListIsEmpty() {
        //Arrange,Act, Assert
        Assertions.assertDoesNotThrow(() -> showAllPeopleCommand.execute(List.of()));
    }

    @Test
    public void should_ShowAllPeople_In_TheApplication() {
        //Arrange
        Members testMember = taskManagementSystemRepository.createMember("Dinko", "Kovachev");
        membersList.add(testMember);
        membersList = taskManagementSystemRepository.getAllMembers();


        //Act,Assert
        Assertions.assertDoesNotThrow(() -> showAllPeopleCommand.execute(List.of()));
    }
}

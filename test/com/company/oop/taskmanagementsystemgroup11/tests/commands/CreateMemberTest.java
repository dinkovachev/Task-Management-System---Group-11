package com.company.oop.taskmanagementsystemgroup11.tests.commands;

import com.company.oop.taskmanagementsystemgroup11.tests.utils.TestUtilities;
import com.company.oop.taskmanagementsytemgroup11.commands.contracts.Command;
import com.company.oop.taskmanagementsytemgroup11.commands.creation.CreateMemberCommand;
import com.company.oop.taskmanagementsytemgroup11.core.TaskManagementSystemRepositoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class CreateMemberTest {

    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    private TaskManagementSystemRepositoryImpl repository;
    private Command createMemberCommand;
    private List<String> params;

    @BeforeEach
    public void before() {
        params = new ArrayList<>();
        repository = new TaskManagementSystemRepositoryImpl();
        createMemberCommand = new CreateMemberCommand(repository);
    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        // Arrange
        List<String> params = TestUtilities.getList(EXPECTED_NUMBER_OF_ARGUMENTS - 1);

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> createMemberCommand.execute(params));
    }

    @Test
    public void should_CreateMember_When_ArgumentsAreValid() {

        params = List.of(
                "firstName",
                "lastName");

        // Act,
        createMemberCommand.execute(params);

        // Assert
        Assertions.assertAll(
                () -> Assertions.assertEquals(
                        ("firstName"), repository.getMemberByUsername("firstName_lastName1").getFirstName()),
                () -> Assertions.assertEquals(
                        ("lastName"), repository.getMemberByUsername("firstName_lastName1").getLastName()));
    }
}

package com.company.oop.taskmanagementsystemgroup11.tests.commands;

import com.company.oop.taskmanagementsystemgroup11.tests.utils.TestUtilities;
import com.company.oop.taskmanagementsytemgroup11.commands.change.ChangeSizeCommand;
import com.company.oop.taskmanagementsytemgroup11.commands.contracts.Command;
import com.company.oop.taskmanagementsytemgroup11.core.TaskManagementSystemRepositoryImpl;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Board;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Team;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;

public class AddMemberTest {
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    private TaskManagementSystemRepositoryImpl repository;
    private Command addMemberCommand;
    private Member member;
    private Team team;
    private Board board;
    private List<String> params;

    @BeforeEach
    public void before() {
        params = new ArrayList<>();
        repository = new TaskManagementSystemRepositoryImpl();
        addMemberCommand = new ChangeSizeCommand(repository);
    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        // Arrange
        List<String> params = TestUtilities.getList(EXPECTED_NUMBER_OF_ARGUMENTS - 1);

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> addMemberCommand.execute(params));
    }

    @Test
    public void should_ChangeStatus_When_ArgumentsAreValid() {
        // Arrange
        params = List.of(
                "validUsername",
                team.getName());

        // Act
        addMemberCommand.execute(params);

        // Assert
        Assertions.assertEquals(
                "validUsername", repository.getMemberByUsername("validUsername").getUsername());
    }

}

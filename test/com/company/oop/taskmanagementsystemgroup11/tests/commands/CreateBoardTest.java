//package com.company.oop.taskmanagementsystemgroup11.tests.commands;
//
//import com.company.oop.taskmanagementsystemgroup11.tests.utils.TestUtilities;
//import com.company.oop.taskmanagementsytemgroup11.commands.creation.CreateBoardCommand;
//import com.company.oop.taskmanagementsytemgroup11.core.TaskManagementSystemRepositoryImpl;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.util.List;
//
//public class CreateBoardTest {
//
//    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
//    private TaskManagementSystemRepositoryImpl repository;
//    private CreateBoardCommand createBoardCommand;
//
//    @BeforeEach
//    public void before() {
//        repository = new TaskManagementSystemRepositoryImpl();
//        createBoardCommand = new CreateBoardCommand(repository);
//    }
//
//    @Test
//    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
//        // Arrange
//        List<String> params = TestUtilities.getList(EXPECTED_NUMBER_OF_ARGUMENTS - 1);
//
//        // Act, Assert
//        Assertions.assertThrows(IllegalArgumentException.class, () -> createBoardCommand.execute(params));
//    }
//
//    @Test
//    public void should_ThrowException_When_BoardNameLengthOutOfBounds() {
//        // Arrange
//        List<String> params = List.of(
//                TestUtilities.getString(CreateBoardCommand.MIN_BOARD_NAME_LEN - 1),
//                TestUtilities.getString(CreateBoardCommand.MIN_TEAM_NAME_LEN + 1));
//
//        // Act, Assert
//        Assertions.assertThrows(IllegalArgumentException.class, () -> createBoardCommand.execute(params));
//    }
//}

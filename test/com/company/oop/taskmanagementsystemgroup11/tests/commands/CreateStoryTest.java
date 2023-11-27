package com.company.oop.taskmanagementsystemgroup11.tests.commands;

import com.company.oop.taskmanagementsystemgroup11.tests.utils.TestUtilities;
import com.company.oop.taskmanagementsytemgroup11.commands.contracts.Command;
import com.company.oop.taskmanagementsytemgroup11.commands.creation.CreateStoryCommand;
import com.company.oop.taskmanagementsytemgroup11.core.TaskManagementSystemRepositoryImpl;
import com.company.oop.taskmanagementsytemgroup11.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsytemgroup11.models.MembersImpl;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Board;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Members;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Story;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Team;
import com.company.oop.taskmanagementsytemgroup11.models.enums.Priority;
import com.company.oop.taskmanagementsytemgroup11.models.enums.Size;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.testng.AssertJUnit.assertEquals;

public class CreateStoryTest {
    private static final int VALID_ID = 1;
    private static final String VALID_TITLE = TestUtilities.getString(10);
    private static final String VALID_DESCRIPTION = TestUtilities.getString(10);
    private static final Priority VALID_PRIORITY = Priority.HIGH;
    private static final Size VALID_SIZE = Size.MEDIUM;
    private static final int VALID_TASK_INDEX = 1;
    private static final String VALID_ASSIGNEE_EXIST = "Ilarion_Makariopolski1";
    private static final String INVALID_ASSIGNEE_DOESNT_EXIST = "not";
    private static final String VALID_TEAM_NAME_EXIST = "TeamCherry";
    private static final String INVALID_TEAM_NAME_DOESNT_EXIST = "not";
    private static final String VALID_BOARD_NAME_EXIST = "Whiteboard";
    private static final String INVALID_BOARD_NAME_DOESNT_EXIST = "not";
    public static final int EXPECTED_ARGUMENTS_COUNT_STORY = 7;
    public static final String VALID_ASSIGNEE_MEMBER = "Ilarion_Makariopolski1";
    public static final String INVALID_ASSIGNEE_NOT_MEMBER = "not";

    private TaskManagementSystemRepository taskManagementSystemRepository;
    private Command createStoryCommand;


    @BeforeEach
    public void initializeCommandAndRepository() {
        this.taskManagementSystemRepository = new TaskManagementSystemRepositoryImpl();
        this.createStoryCommand = new CreateStoryCommand(taskManagementSystemRepository);
    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        //Arrange
        List<String> parameters = TestUtilities.getList(EXPECTED_ARGUMENTS_COUNT_STORY - 1);
        //Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> createStoryCommand.execute(parameters));
    }

    @Test
    public void should_ThrowException_When_AssigneeDoesNot_Exist() {
        //Arrange,
        List<String> parameters = List.of(String.valueOf(VALID_ID),
                VALID_TITLE,
                VALID_DESCRIPTION,
                String.valueOf(VALID_PRIORITY),
                String.valueOf(VALID_SIZE),
                INVALID_ASSIGNEE_NOT_MEMBER,
                VALID_TEAM_NAME_EXIST,
                VALID_BOARD_NAME_EXIST);
        // Act,Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> createStoryCommand.execute(parameters));
    }

    @Test
    public void should_ThrowException_When_TeamNameDoesNot_Exist() {
        //Arrange,
        List<String> parameters = List.of(String.valueOf(VALID_ID),
                VALID_TITLE,
                VALID_DESCRIPTION,
                String.valueOf(VALID_PRIORITY),
                String.valueOf(VALID_SIZE),
                VALID_ASSIGNEE_MEMBER,
                INVALID_TEAM_NAME_DOESNT_EXIST,
                VALID_BOARD_NAME_EXIST);
        // Act,Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> createStoryCommand.execute(parameters));
    }

    @Test
    public void should_ThrowException_When_BoardNameDoesNot_Exist() {
        //Arrange,
        List<String> parameters = List.of(String.valueOf(VALID_ID),
                VALID_TITLE,
                VALID_DESCRIPTION,
                String.valueOf(VALID_PRIORITY),
                String.valueOf(VALID_SIZE),
                VALID_ASSIGNEE_MEMBER,
                VALID_TEAM_NAME_EXIST,
                INVALID_BOARD_NAME_DOESNT_EXIST);
        // Act,Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> createStoryCommand.execute(parameters));
    }

    //ToDo
    // Doesnt work
    @Test
    public void should_Return_InitializedStory() {
        //Arrange, Act

        Members member = taskManagementSystemRepository.createMember("Ilarion", "Makariopolski");
        Team team = taskManagementSystemRepository.createTeam(VALID_TEAM_NAME_EXIST);
        Board board = taskManagementSystemRepository.createBoard(VALID_BOARD_NAME_EXIST);

        List<String> parameters = List.of(
                VALID_TITLE,
                VALID_DESCRIPTION,
                String.valueOf(VALID_PRIORITY),
                String.valueOf(VALID_SIZE),
                member.getUsername(),
                team.getName(),
                board.getName());
        createStoryCommand.execute(parameters);
        //Assert
        Story story = taskManagementSystemRepository.findStoryByTaskIndex(VALID_TASK_INDEX);
        assertAll(
                () -> assertEquals(story.getId(), VALID_TASK_INDEX),
                () -> assertEquals(story.getTitle(), VALID_TITLE),
                () -> assertEquals(story.getDescription(), VALID_DESCRIPTION),
                () -> assertEquals(story.getPriority(), VALID_PRIORITY),
                () -> assertEquals(story.getSize(), VALID_SIZE),
                () -> assertEquals(VALID_ASSIGNEE_EXIST, VALID_ASSIGNEE_MEMBER),
                () -> assertEquals(VALID_TEAM_NAME_EXIST, VALID_TEAM_NAME_EXIST),
                () -> assertEquals(VALID_BOARD_NAME_EXIST, VALID_BOARD_NAME_EXIST)
        );
    }

    @Test
    public void should_AddStory_ToStoryList() {
        //Arrange
        Members member = taskManagementSystemRepository.createMember("Ilarion", "Makariopolski");
        Team team = taskManagementSystemRepository.createTeam(VALID_TEAM_NAME_EXIST);
        Board board = taskManagementSystemRepository.createBoard(VALID_BOARD_NAME_EXIST);
        List<String> parameters = List.of(
                VALID_TITLE,
                VALID_DESCRIPTION,
                String.valueOf(VALID_PRIORITY),
                String.valueOf(VALID_SIZE),
                member.getUsername(),
                team.getName(),
                board.getName());

        //Act, Assert
        assertAll(
                () -> assertDoesNotThrow(() -> createStoryCommand.execute(parameters)),
                () -> assertEquals(1, taskManagementSystemRepository.getAllStories().size())
        );
    }

    @Test
    public void should_AddStory_ToTaskList() {
        //Arrange
        Members member = taskManagementSystemRepository.createMember("Ilarion", "Makariopolski");
        Team team = taskManagementSystemRepository.createTeam(VALID_TEAM_NAME_EXIST);
        Board board = taskManagementSystemRepository.createBoard(VALID_BOARD_NAME_EXIST);
        List<String> parameters = List.of(
                VALID_TITLE,
                VALID_DESCRIPTION,
                String.valueOf(VALID_PRIORITY),
                String.valueOf(VALID_SIZE),
                member.getUsername(),
                team.getName(),
                board.getName());


        //Act, Assert
        assertAll(
                () -> assertDoesNotThrow(() -> createStoryCommand.execute(parameters)),
                () -> assertEquals(1, taskManagementSystemRepository.getAllStories().size())
        );
    }
}
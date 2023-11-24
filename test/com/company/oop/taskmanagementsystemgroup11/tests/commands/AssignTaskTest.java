package com.company.oop.taskmanagementsystemgroup11.tests.commands;

import com.company.oop.taskmanagementsystemgroup11.tests.utils.TaskConstants;
import com.company.oop.taskmanagementsystemgroup11.tests.utils.TestUtilities;
import com.company.oop.taskmanagementsytemgroup11.commands.assign.AssignTaskCommand;
import com.company.oop.taskmanagementsytemgroup11.core.TaskManagementSystemRepositoryImpl;
import com.company.oop.taskmanagementsytemgroup11.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Members;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Story;
import com.company.oop.taskmanagementsytemgroup11.models.enums.Priority;
import com.company.oop.taskmanagementsytemgroup11.models.enums.Size;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class AssignTaskTest {
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 3;

    private AssignTaskCommand assignTaskCommand;
    private TaskManagementSystemRepository taskManagementSystemRepository;

    @BeforeEach
    public void initializeCommandAndRepository() {
        taskManagementSystemRepository = new TaskManagementSystemRepositoryImpl();
        assignTaskCommand = new AssignTaskCommand(taskManagementSystemRepository);
    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        //Arrange
        List<String> parameters = TestUtilities.getList(EXPECTED_NUMBER_OF_ARGUMENTS - 1);
        //Act,Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> assignTaskCommand.execute(parameters));
    }

    @Test
    public void should_AssignMemberToTask_WhenInputIsValid() {
        //Arrange
        Members testMember = taskManagementSystemRepository.createMember("Dinko", "Kovachev");
        Story story = taskManagementSystemRepository.createStory(TestUtilities.getString(TaskConstants.VALID_TITLE),
                TestUtilities.getString(TaskConstants.VALID_DESCRIPTION), Priority.HIGH, Size.LARGE,
                TestUtilities.getString(TaskConstants.VALID_USERNAME),
                TestUtilities.getString(TaskConstants.VALID_TEAM_NAME),
                TestUtilities.getString(TaskConstants.VALID_BOARD_NAME));

        List<String> parameters = List.of(String.valueOf(story.getType()),
                story.getTitle(),
                String.valueOf(taskManagementSystemRepository.findTaskByID(story.getId()).getId()),
                testMember.getUsername());
        //Act,Assert
        assignTaskCommand.execute(parameters);
    }
}

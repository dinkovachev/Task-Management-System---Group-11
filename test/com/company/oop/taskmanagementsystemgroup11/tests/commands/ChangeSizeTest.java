package com.company.oop.taskmanagementsystemgroup11.tests.commands;

import com.company.oop.taskmanagementsystemgroup11.tests.utils.TaskConstants;
import com.company.oop.taskmanagementsystemgroup11.tests.utils.TestUtilities;
import com.company.oop.taskmanagementsytemgroup11.commands.change.ChangeSizeCommand;
import com.company.oop.taskmanagementsytemgroup11.commands.contracts.Command;
import com.company.oop.taskmanagementsytemgroup11.core.TaskManagementSystemRepositoryImpl;
import com.company.oop.taskmanagementsytemgroup11.models.enums.Size;
import com.company.oop.taskmanagementsytemgroup11.models.enums.TaskType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.company.oop.taskmanagementsystemgroup11.tests.models.StoryImplTest.VALID_PRIORITY;

public class ChangeSizeTest {
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 3;
    private TaskManagementSystemRepositoryImpl repository;
    private Command changeSizeCommand;
    private List<String> params;

    @BeforeEach
    public void before() {
        params = new ArrayList<>();
        repository = new TaskManagementSystemRepositoryImpl();
        changeSizeCommand = new ChangeSizeCommand(repository);
    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        // Arrange
        List<String> params = TestUtilities.getList(EXPECTED_NUMBER_OF_ARGUMENTS - 1);

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> changeSizeCommand.execute(params));
    }

    @Test
    public void should_ChangeStatus_When_ArgumentsAreValid() {
//         Arrange


        repository.createStory(
                TestUtilities.getString(TaskConstants.VALID_TITLE),
                TestUtilities.getString(TaskConstants.VALID_DESCRIPTION),
                VALID_PRIORITY,
                Size.LARGE,
                TestUtilities.getString(TaskConstants.VALID_USERNAME),
                TestUtilities.getString(TaskConstants.VALID_TEAM_NAME),
                TestUtilities.getString(TaskConstants.VALID_BOARD_NAME));

        // Act
        params = List.of(
                TaskType.STORY.toString(),
                Size.SMALL.toString(),
                "1");

        changeSizeCommand.execute(params);

        // Assert
        Assertions.assertEquals(Size.SMALL, repository.findStoryByTaskIndex(1).getSize());
    }
}

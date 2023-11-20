package com.company.oop.taskmanagementsytemgroup11.models;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.ActivityLog;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Board;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Task;
import com.company.oop.taskmanagementsytemgroup11.utils.ValidationHelpers;

import java.util.ArrayList;
import java.util.List;

public class BoardImpl implements Board {

    private static final int MINIMUM_SYMBOLS = 5;
    private static final int MAXIMUM_SYMBOLS = 15;
    private static final String BOARD_NAME_ERR_MSG = String.format(
            "The Board name's length cannot be less than %d or more than %d symbols long.",
            MINIMUM_SYMBOLS, MAXIMUM_SYMBOLS);
    private static final String NEW_TASK_WITH_NAME_ADDED_TO_BOARD_MESSAGE = "New task %s with name %s added to the board";
    private static final String NEW_BOARD_CREATED_MESSAGE = "New board with name %s created";
    private final List<Board> boards = new ArrayList<>();
    private final List<Task> tasksToAddToBoard = new ArrayList<>();
    private final List<ActivityLog> boardsActivityHistory = new ArrayList<>();
    private String name;


    public BoardImpl(String name) {
        setName(name);
        addEventToActivityLogHistory(String.format(NEW_BOARD_CREATED_MESSAGE, name));
       // this.activityHistory = new ArrayList<>();
       // this.tasksToAddToBoard = new ArrayList<>();
    }

    @Override
    public void addTask(Task task) {
        tasksToAddToBoard.add(task);
        addEventToActivityLogHistory(String.format(
                NEW_TASK_WITH_NAME_ADDED_TO_BOARD_MESSAGE,
                task.getId(),
                task.getTitle()));
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        ValidationHelpers.validateIntRange(name.length(), MINIMUM_SYMBOLS, MAXIMUM_SYMBOLS, BOARD_NAME_ERR_MSG);
        this.name = name;

    }
    //ToDo double check this issue

    public void addEventToActivityLogHistory(String event) {
        boardsActivityHistory.add(new ActivityLogImpl(event));
    }

    public String displayActivityLogHistory() {
        StringBuilder result = new StringBuilder();
        for (ActivityLog activityLog : boardsActivityHistory) {
            result.append(activityLog.displayInfo()).append(System.lineSeparator());
        }
        return result.toString();
    }

    @Override
    public String getAsString() {
        return """
                Name: %s
                """.formatted(name);
    }

}



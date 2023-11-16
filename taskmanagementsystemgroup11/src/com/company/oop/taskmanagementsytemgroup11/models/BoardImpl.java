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
    public static final String BOARD_NAME_ERR_MSG = String.format(
            "The Board name's length cannot be less than %d or more than %d symbols long.",
            MINIMUM_SYMBOLS, MAXIMUM_SYMBOLS);
    private final List<Board> boards;
    private String name;


    public BoardImpl(String name) {
        setName(name);
        this.boards = new ArrayList<>();
    }

    @Override
    public void addTask(Task task) {
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<Board> getBoards() {
        return new ArrayList<>(boards);
    }

    public void setName(String name) {
        ValidationHelpers.validateIntRange(name.length(), MINIMUM_SYMBOLS, MAXIMUM_SYMBOLS, BOARD_NAME_ERR_MSG);
        this.name = name;
    }
    //ToDo double check this issue

    @Override
    public void addBoard(Board boards) {
        this.boards.add(boards);
    }


    @Override
    public String getAsString() {
        return """
                Name: %s;
                """.formatted(name);
    }
}



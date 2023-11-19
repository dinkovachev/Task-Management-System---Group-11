package com.company.oop.taskmanagementsytemgroup11.models.contracts;

import java.util.List;

public interface Board extends Printable {

    void addTask(Task task);

    String getName();

    void addBoard(Board board);
        String displayActivityLogHistory();


    List<Board> getBoards();
}

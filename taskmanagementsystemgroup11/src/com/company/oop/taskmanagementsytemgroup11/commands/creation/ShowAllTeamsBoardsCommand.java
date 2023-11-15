package com.company.oop.taskmanagementsytemgroup11.commands.creation;


import com.company.oop.taskmanagementsytemgroup11.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Board;
import com.company.oop.taskmanagementsytemgroup11.utils.ListingHelpers;

import java.util.List;

public class ShowAllTeamsBoardsCommand extends BaseCommand {
    private final List<Board> boards;

    public ShowAllTeamsBoardsCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
        boards = taskManagementSystemRepository.getAllTeamsBoards();
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        if (boards.isEmpty()) {
            return "There are no registered boards.";
        }
        return ListingHelpers.boardToString(boards);
    }
}

package com.company.oop.taskmanagementsytemgroup11.commands.creation;


import com.company.oop.taskmanagementsytemgroup11.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Team;
import com.company.oop.taskmanagementsytemgroup11.utils.ValidationHelpers;

import java.util.List;

public class ShowAllTeamsBoardsCommand extends BaseCommand {
//    private final List<Board> boards;
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    private static final String SHOW_ALL_TEAM_NO_BOARDS_MESSAGE = "There no boards in %s team.";
    private static final String SHOW_TEAM_BOARDS_MESSAGE = "%s team boards: %s:";



    public ShowAllTeamsBoardsCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
 //       boards = taskManagementSystemRepository.getAllTeamsBoards();

    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String teamName = parameters.get(0);

        Team team = getTaskManagementSystemRepository().getTeamByName(teamName);
        List boards = team.getTeamBoards();
//        if (teamName.length()==1) {
//            return String.format("There are no team activity in team %s.", teamName);
//        }

        if (boards.isEmpty()) {
            return String.format(SHOW_ALL_TEAM_NO_BOARDS_MESSAGE, team.getName());
        }
        return String.format(SHOW_TEAM_BOARDS_MESSAGE, team.getName(), team.getTeamBoards());
//        return ListingHelpers.boardToString(boards);
    }
}

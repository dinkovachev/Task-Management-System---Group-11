package com.company.oop.taskmanagementsytemgroup11.commands.creation;

import com.company.oop.taskmanagementsytemgroup11.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Board;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Members;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Team;
import com.company.oop.taskmanagementsytemgroup11.utils.ParsingHelpers;
import com.company.oop.taskmanagementsytemgroup11.utils.ValidationHelpers;

import java.util.List;

public class ShowTeamActivityCommand extends BaseCommand {

    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    private static final String INVALID_INPUT_MESSAGE = "Invalid input. Expected a number.";
    private static final String SHOW_TEAM_ACTIVITY_MESSAGE = "%s team activity ";


    public ShowTeamActivityCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String teamName = parameters.get(0);
 //       int id = ParsingHelpers.tryParseInteger(parameters.get(1), INVALID_INPUT_MESSAGE);
        Team team = getTaskManagementSystemRepository().getTeamByName(teamName);
        List<Board> boards = team.getBoards();
        if (boards.isEmpty()) {
            return String.format("There are no boards in team %s.", teamName);
        }

        return String.format(SHOW_TEAM_ACTIVITY_MESSAGE,/*id,*/ team.getBoards().toString());
 //       return showTeamActivity(team, id);
    }
//    private String showTeamActivity(String team, int id){
//        Team team = getTaskManagementSystemRepository().getTeamByUsername(team);
//        ActivityLog activityLog = getTaskManagementSystemRepository().ge
//
//        return String.format(SHOW_TEAM_ACTIVITY_MESSAGE,team);
//    }
}

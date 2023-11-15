package com.company.oop.taskmanagementsytemgroup11.models;

import com.company.oop.taskmanagementsytemgroup11.models.contracts.*;

import java.util.ArrayList;
import java.util.List;

public class HistoryImpl extends ActivityLogImpl implements History {

    private final List<Team> teamActivity;
    private final List<Board> boardActivity;
    private final List<Members> membersActivity;



    public HistoryImpl(List<Comment> activityLogList, List<History> historyChanges, List<Team> teamActivity, List<Board> boardActivity, List<Members> membersActivity) {
        super(activityLogList, historyChanges);
        this.teamActivity = teamActivity;
        this.boardActivity = boardActivity;
        this.membersActivity = membersActivity;
    }


    public List<Team> getTeamActivity() {
        return new ArrayList<>(teamActivity);
    }

    public List<Board> getBoardActivity() {
        return new ArrayList<>(boardActivity);
    }

    public List<Members> getMembersActivity() {
        return new ArrayList<>(membersActivity);
    }

    @Override
    public void addTeamActivity(Team activityLog) {
        teamActivity.add(activityLog);
    }

    @Override
    public void addMemberActivity(Members activityLog) {
        membersActivity.add(activityLog);
    }

    @Override
    public void addBoardActivity(Board activityLog) {
        boardActivity.add(activityLog);
    }
}

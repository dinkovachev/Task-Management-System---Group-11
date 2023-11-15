package com.company.oop.taskmanagementsytemgroup11.models;

import com.company.oop.taskmanagementsytemgroup11.models.contracts.*;

import java.util.ArrayList;
import java.util.List;

public class ActivityLogImpl implements ActivityLog {

    //ToDo(Done) - Dinko
    // Added another interface History to keep track of the historyChanges in ActivityLogImpl

    private final List<Comment> activityLogList;
    private final List<History> historyChanges;
    private final List<Team> teamActivity;
    private final List<Board> boardActivity;
    private final List<Members> membersActivity;

    private String activityLog;

    public ActivityLogImpl(List<Comment> activityLogList, List<History> historyChanges, String activityLog) {
        this.activityLogList = new ArrayList<>();
        this.historyChanges = new ArrayList<>();
        setActivityLog(activityLog);

    }

    public List<Comment> getActivityLogList() {

        return new ArrayList<>(activityLogList);
    }

    public List<History> getHistoryChanges() {

        return new ArrayList<>(historyChanges);
    }

    public String getActivityLog() {
        return activityLog;
    }

    private void setActivityLog(String activityLog) {
        this.activityLog = activityLog;
    }

    @Override
    public String showTeamActivity(Team team) {
        return null;
    }

    @Override
    public String showMemberActivity(Team team) {
        return null;
    }

    @Override
    public String showBoardActivity(Team team) {
        return null;
    }


    //ToDo - Dinko
    // Finish the remaining methods from the interface


}

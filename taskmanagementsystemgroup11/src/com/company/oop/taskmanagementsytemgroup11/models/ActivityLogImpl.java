package com.company.oop.taskmanagementsytemgroup11.models;

import com.company.oop.taskmanagementsytemgroup11.models.contracts.ActivityLog;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Comment;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.History;

import java.util.ArrayList;
import java.util.List;

public class ActivityLogImpl implements ActivityLog{

    //ToDo(Done) - Dinko
    // Added another interface History to keep track of the historyChanges in ActivityLogImpl

    private final List<Comment> activityLogList;
    private final List<History> historyChanges;

    public ActivityLogImpl(List<Comment> activityLogList, List<History> historyChanges) {
        this.activityLogList = new ArrayList<>();
        this.historyChanges = new ArrayList<>();
    }

    public List<Comment> getActivityLogList() {
        return new ArrayList<>(activityLogList);
    }

    public List<History> getHistoryChanges() {
        return new ArrayList<>(historyChanges);
    }


    //ToDo - Dinko
    // Finish the remaining methods from the interface
    @Override
    public String showTeamActivity() {
        return null;
    }

    @Override
    public String showMemberActivity() {
        return null;
    }

    @Override
    public String showBoardActivity() {
        return null;
    }
}

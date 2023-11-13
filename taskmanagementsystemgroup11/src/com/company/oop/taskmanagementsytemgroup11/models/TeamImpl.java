package com.company.oop.taskmanagementsytemgroup11.models;

import com.company.oop.taskmanagementsytemgroup11.models.contracts.Board;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Members;

import java.util.ArrayList;
import java.util.List;

public class TeamImpl {

    private String name;

    private List<Members> members;

    private List<Board> boards;

    public TeamImpl(String name) {
        setName(name);
        this.members = new ArrayList<>();
        this.boards = new ArrayList<>();
    }

    public String getName() {
        return name;
    }
    public List<Members> getMembers() {
        return new ArrayList<>(members);
    }
    public List<Board> getBoards() {
        return new ArrayList<>(boards);
    }
    private void setName(String name) {
        this.name = name;
    }
    private void setMembers(List<Members> members) {
        this.members = members;
    }
    private void setBoards(List<Board> boards) {
        this.boards = boards;
    }
}

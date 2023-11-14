package com.company.oop.taskmanagementsytemgroup11.core;

import com.company.oop.taskmanagementsytemgroup11.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsytemgroup11.models.*;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.*;
import com.company.oop.taskmanagementsytemgroup11.models.enums.Priority;
import com.company.oop.taskmanagementsytemgroup11.models.enums.Severity;
import com.company.oop.taskmanagementsytemgroup11.models.enums.Size;

import java.util.ArrayList;
import java.util.List;

public class TaskManagementSystemRepositoryImpl implements TaskManagementSystemRepository {
    private int nextId;
    private final List<Members> members = new ArrayList<>();
    private final List<Team> teams = new ArrayList<>();
    private final List<Board> boards = new ArrayList<>();

    public TaskManagementSystemRepositoryImpl() {
        nextId = 0;
    }


    //ToDo - Dinko
    // finish the remaining methods
    @Override
    public List<Members> getAllMembers() {
        return new ArrayList<>(members);
    }

    @Override
    public List<Team> getAllTeams() {
        return new ArrayList<>(teams);
    }

    //ToDo double check how to display the teamBoards only not all boards
    @Override
    public List<Board> getAllTeamsBoards() {
        return new ArrayList<>(boards);
    }

    @Override
    public List<Team> getAllTeamMembers() {
        return null;
    }

    @Override
    public Members getMemberById() {
        return null;
    }
    @Override
    public Task findTaskByID(int id) {
        return null;
    }

    @Override
    public Members createMember(String firstName, String lastName) {
        Members member = new MembersImpl(firstName, lastName);
        this.members.add(member);
        return member;
    }

    @Override
    public Team createTeam(String name) {
        Team team = new TeamImpl(name);
        this.teams.add(team);
        return team;
    }


    @Override
    public Board createBoard(String name) {
        Board board = new BoardImpl(name);
        this.boards.add(board);
        return board;
    }

    @Override
    public Comment createComment(String content, String author) {
        return new CommentImpl(content, author);
    }

    @Override
    public Bug createBug(int id, String title, String description, String stepsToReproduce, Priority priority, Severity severity, String assignee) {
        return new BugImpl(++nextId, title, description, stepsToReproduce, priority, severity, assignee);
    }

    @Override
    public Story createStory(int id, String title, String description, Priority priority, Size size, String assignee) {
        return new StoryImpl(++nextId, title, description, priority, size, assignee);
    }

    @Override
    public Feedback createFeedback(int id, String title, String description, int rating) {
        return new FeedbackImpl(++nextId, title, description, rating);
    }

    @Override
    public boolean memberExist(String memberName) {
        boolean exists = false;
        for (Members members : getAllMembers()) {
            if (members.getUsername().equalsIgnoreCase(memberName)) {
                exists = true;
                break;
            }
        }
        return exists;
    }

    @Override
    public boolean teamExist(String name) {
        boolean exists = false;
        for (Team teams : getAllTeams()) {
            if (teams.getName().equalsIgnoreCase(name)) {
                exists = true;
                break;
            }
        }
        return exists;
    }

    public boolean boardExist(String name) {
        boolean exists = false;
        for (Board boards : getAllTeamsBoards()) {
            if (boards.getName().equalsIgnoreCase(name)) {
                exists = true;
                break;
            }
        }
        return exists;
    }
}

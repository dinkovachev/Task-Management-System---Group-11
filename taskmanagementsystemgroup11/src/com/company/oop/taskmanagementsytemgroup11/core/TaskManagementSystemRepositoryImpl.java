package com.company.oop.taskmanagementsytemgroup11.core;

import com.company.oop.taskmanagementsytemgroup11.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsytemgroup11.models.*;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.*;
import com.company.oop.taskmanagementsytemgroup11.models.enums.Priority;
import com.company.oop.taskmanagementsytemgroup11.models.enums.Severity;
import com.company.oop.taskmanagementsytemgroup11.models.enums.Size;

import java.util.List;

public class TaskManagementSystemRepositoryImpl implements TaskManagementSystemRepository {
    private int nextId;

    public TaskManagementSystemRepositoryImpl() {
        nextId = 0;
    }


    //ToDo - Dinko
    // finish the remaining methods
    @Override
    public List<Members> getAllMembers() {
        return null;
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
    public Members createMember(String username, String firstName, String lastName) {
        return new MembersImpl(username, firstName, lastName);
    }

    @Override
    public Team createTeam(String name) {
        return new TeamImpl(name);
    }

    @Override
    public Board createBoard(String name) {
        return new BoardImpl(name);
    }

    @Override
    public Comment createComment(String content, String author) {
        return new CommentImpl(content, author);
    }

    @Override
    public Bug createBug(String title, String description, String stepsToReproduce, Priority priority, Severity severity, Members members) {
        return new BugImpl(++nextId, title, description, stepsToReproduce, priority, severity, members);
    }

    @Override
    public Story createStory(String title, String description, Priority priority, Size size, Members members) {
        return new StoryImpl(++nextId, title, description, priority, size, members);
    }

    @Override
    public Feedback createFeedback(String title, String description, int rating) {
        return new FeedbackImpl(++nextId, title, description, rating);
    }

}

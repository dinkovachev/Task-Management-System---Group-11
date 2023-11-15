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
    private int nextPersonId;
    private final List<Members> members = new ArrayList<>();
    private final List<Team> teams = new ArrayList<>();
    private final List<Board> boards = new ArrayList<>();
    private final List<Bug> bugs = new ArrayList<>();
    private final List<Story> stories = new ArrayList<>();
    private final List<Feedback> feedbacks = new ArrayList<>();

    public TaskManagementSystemRepositoryImpl() {

        nextId = 0;
        nextPersonId = 0;
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
    public Members getMemberByUsername(String username) {
        return null;
    }

    @Override
    public Team getTeamByUsername(String username) {
        return null;
    }

    @Override
    public Task findTaskByID(int id) {

        return null;
    }

    @Override
    public Members createMember(int personId, String firstName, String lastName) {
        Members member = new MembersImpl(++nextPersonId, firstName, lastName);
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
        Bug bug = new BugImpl(++nextId, title, description, stepsToReproduce, priority, severity, assignee);
        this.bugs.add(bug);
        return bug;
    }

    @Override
    public Bug findBugByIndex(int bugIndex) {
        Bug bug = bugs
                .stream()
                .filter(b -> b.getBugs().get(bugIndex).equals(bugIndex))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("No such bug with index %d", bugIndex)));
        return bug;
    }

    @Override
    public Story createStory(int id, String title, String description, Priority priority, Size size, String assignee) {
        Story story = new StoryImpl(++nextId, title, description, priority, size, assignee);
        this.stories.add(story);
        return story;
    }

    @Override
    public Story findStoryByIndex(int storyIndex) {
        Story story = stories
                .stream()
                .filter(s -> s.getStories().get(storyIndex).equals(storyIndex))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("No such story with index %d", storyIndex)));
        return story;
    }

    @Override
    public Feedback createFeedback(int id, String title, String description, int rating) {
        Feedback feedback = new FeedbackImpl(++nextId, title, description, rating);
        this.feedbacks.add(feedback);
        return feedback;
    }

    @Override
    public Feedback findFeedbackByIndex(int feedbackIndex) {
        Feedback feedback = feedbacks
                .stream()
                .filter((f -> f.getFeedbacks().get(feedbackIndex).equals(feedbackIndex)))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("No such feedback with index %d", feedbackIndex)));

        return feedback;
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
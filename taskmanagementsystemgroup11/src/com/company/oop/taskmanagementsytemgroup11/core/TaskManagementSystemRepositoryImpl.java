package com.company.oop.taskmanagementsytemgroup11.core;

import com.company.oop.taskmanagementsytemgroup11.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsytemgroup11.models.*;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.*;
import com.company.oop.taskmanagementsytemgroup11.models.enums.Priority;
import com.company.oop.taskmanagementsytemgroup11.models.enums.Severity;
import com.company.oop.taskmanagementsytemgroup11.models.enums.Size;
import com.company.oop.taskmanagementsytemgroup11.models.enums.TaskType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.lang.String.format;

public class TaskManagementSystemRepositoryImpl implements TaskManagementSystemRepository {
    private static final String NO_SUCH_TEAM = "No such team with name %s.";
    private final static String NO_SUCH_MEMBER = "There is no user with username %s!";
    private static final String NO_SUCH_BOARD = "There is no such board with name %s";
    public static final String INVALID_TASK_INDEX_MSG = "Invalid task index.";
    private int lastId;
    private int lastMemberId;
    private final List<Members> members = new ArrayList<>();
    private final List<Team> teams = new ArrayList<>();
    private final List<Board> boards = new ArrayList<>();
    private final List<Bug> bugs = new ArrayList<>();
    private final List<Story> stories = new ArrayList<>();
    private final List<Feedback> feedbacks = new ArrayList<>();
    private final List<Task> tasks = new ArrayList<>();

    public TaskManagementSystemRepositoryImpl() {
        lastId = 1;
        lastMemberId = 0;
    }

    @Override
    public List<Members> getAllMembers() {
        return new ArrayList<>(members);
    }

    @Override
    public List<Team> getAllTeams() {
        return new ArrayList<>(teams);
    }

    @Override
    public List<Board> getAllTeamsBoards() {
        return new ArrayList<>(boards);
    }

    @Override
    public Members createMember(String firstName, String lastName) {
        int nextMemberId = lastMemberId + 1;
        Members member = new MembersImpl(nextMemberId, firstName, lastName);
        this.members.add(member);
        lastMemberId = nextMemberId;

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
    public Bug createBug(String title, String description, String stepsToReproduce, Priority priority, Severity severity, String assignee, int taskIndexBug, String teamNameBug, String boardBug) {
        Bug bug = new BugImpl(lastId, title, description, stepsToReproduce, priority, severity, assignee,
                taskIndexBug, teamNameBug, boardBug);
        ++lastId;
        this.bugs.add(bug);
        this.tasks.add(bug);
        return bug;
    }


    @Override
    public Story createStory(String title, String description, Priority priority, Size size,
                             String assignee, int taskIndex, String teamNameStory, String board) {
        Story story = new StoryImpl(lastId, title, description, priority, size, assignee, taskIndex, teamNameStory,
                board);
        ++lastId;
        this.stories.add(story);
        this.tasks.add(story);

        return story;
    }

    @Override
    public Feedback createFeedback(TaskType type, String title, String description, int rating,
                                   int taskIndexFeedback, String teamName, String board) {
        int nextId = lastId + 1;
        Feedback feedback = new FeedbackImpl(++lastId, title, description, rating, taskIndexFeedback, teamName, board);
        lastId = nextId;
        this.feedbacks.add(feedback);
        this.stories.add(null);
        this.bugs.add(null);
        this.tasks.add(feedback);

        return feedback;
    }

    @Override
    public Members getMemberByUsername(String username) {
        Members member = members.stream().filter(m -> m.getUsername().equalsIgnoreCase(username)).findFirst().
                orElseThrow(() -> new IllegalArgumentException(String.format(NO_SUCH_MEMBER, username)));
        return member;
    }

    @Override
    public Team getTeamByName(String name) {
        Team team = teams.stream()
                .filter(t -> t.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format(NO_SUCH_TEAM, name)));
        return team;
    }

    @Override
    public Board getBoardByName(String name) {
        Board board = boards.stream().filter(m -> m.getName().equalsIgnoreCase(name)).findFirst().
                orElseThrow(() -> new IllegalArgumentException(String.format(NO_SUCH_BOARD, name)));
        return board;
    }

    @Override
    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks);
    }

    @Override
    public Task findTaskByID(int taskIndex) {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new IllegalArgumentException(INVALID_TASK_INDEX_MSG);
        } else {
            return tasks.get(taskIndex);
        }
    }

    @Override
    public Story findStoryByTaskIndex(int taskIndex) {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new IllegalArgumentException(INVALID_TASK_INDEX_MSG);
        }
        return stories.get(taskIndex);
    }

    @Override
    public TaskType validateTaskTypeEqualsInputType(TaskType type, int taskIndex) {
        if (!type.equals(tasks.get(taskIndex).getType())) {
            throw new IllegalArgumentException(format("%s is invalid task type.", type));
        }
        return type;
    }

    @Override
    public Bug findBugByTaskIndex(int taskIndex) {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new IllegalArgumentException(INVALID_TASK_INDEX_MSG);
        }
        return bugs.get(taskIndex);
    }

    @Override
    public Feedback findFeedbackByTaskIndex(int taskIndex) {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new IllegalArgumentException(INVALID_TASK_INDEX_MSG);
        }
        return feedbacks.get(taskIndex);
    }

    @Override
    public boolean memberExistsInTeam(Members member, Team team) {
        return false;
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

    @Override
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

    @Override
    public int getLastId() {
        return this.lastId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskManagementSystemRepositoryImpl that = (TaskManagementSystemRepositoryImpl) o;
        return lastId == that.lastId
                && lastMemberId == that.lastMemberId
                && Objects.equals(members, that.members)
                && Objects.equals(teams, that.teams)
                && Objects.equals(boards, that.boards)
                && Objects.equals(bugs, that.bugs)
                && Objects.equals(stories, that.stories)
                && Objects.equals(feedbacks, that.feedbacks)
                && Objects.equals(tasks, that.tasks);
    }
}
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

import static java.lang.String.format;

public class TaskManagementSystemRepositoryImpl implements TaskManagementSystemRepository {
    private static final String NO_SUCH_TEAM = "No such team with name %s.";
    private final static String NO_SUCH_MEMBER = "There is no user with username %s!";
    private static final String NO_SUCH_BOARD = "There is no such board with name";
    public static final String INVALID_TASK_INDEX_MSG = "Invalid task index.";
    private int nextId;
    private int nextPersonId;
    private final List<Members> members = new ArrayList<>();
    private final List<Team> teams = new ArrayList<>();
    private final List<Board> boards = new ArrayList<>();
    private final List<Bug> bugs = new ArrayList<>();
    private final List<Story> stories = new ArrayList<>();
    private final List<Feedback> feedbacks = new ArrayList<>();
    private final List<Task> tasks = new ArrayList<>();
    private final List<ActivityLog> activityLogList = new ArrayList<>();


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

    public List<ActivityLog> getAllActivities() {
        return new ArrayList<>(activityLogList);
    }


    @Override
    public Members createMember(String firstName, String lastName) {
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
    public Bug createBug(TaskType type, String title, String description, String stepsToReproduce, Priority priority,
                         Severity severity, String assignee, int taskIndexBug) {
        Bug bug = new BugImpl(++nextId, title, description, stepsToReproduce, priority, severity, assignee, taskIndexBug);
        this.bugs.add(bug);
        this.feedbacks.add(null);
        this.stories.add(null);
        this.tasks.add(bug);
        return bug;
    }

    @Override
    public Story createStory(TaskType type, String title, String description,
                             Priority priority, Size size, String assignee, int taskIndex) {
        Story story = new StoryImpl(++nextId, title, description, priority, size, assignee, taskIndex);
        this.stories.add(story);
        this.feedbacks.add(null);
        this.bugs.add(null);
        this.tasks.add(story);
        return story;
    }

    @Override
    public Feedback createFeedback(TaskType type, String title, String description, int rating, int taskIndexFeedback) {
        Feedback feedback = new FeedbackImpl(++nextId, title, description, rating, taskIndexFeedback);
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
    public Members getMemberById(int id) {
        Members member = members.stream()
                .filter(b -> b.getAllTeamMembers().get(id).equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("No such bug with index %d", id)));
        return member;
    }

//    @Override
//    public Task findTaskByID(int id) {
//        Task task = tasks
//                .stream()
//                .filter(b -> b.getAllTasks().get(id).equals(id))
//                .findFirst()
//                .orElseThrow(() -> new IllegalArgumentException(String.format("No such bug with index %d", id)));
//        return task;
//    }

    @Override
    public Task findTaskByID(int taskIndex) {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new IllegalArgumentException(INVALID_TASK_INDEX_MSG);
        } else {
            return tasks.get(taskIndex);
        }
    }


//    @Override
//    public Bug findBugByIndex(int bugIndex) {
//        Bug bug = bugs
//                .stream()
//                .filter(b -> b.getBugs().get(bugIndex).equals(bugIndex))
//                .findFirst()
//                .orElseThrow(() -> new IllegalArgumentException(String.format("No such bug with index %d", bugIndex)));
//        return bug;
//    }

//    @Override
//    public Bug findBugByIndex(int bugIndex) {
//        if (bugIndex < 0 || bugIndex >= tasks.size()) {
//            throw new IllegalArgumentException(INVALID_TASK_INDEX_MSG);
//        }
//        return tasks.get(tasks.size() - bugIndex - 1);
//    }
    // find team by name - teamMembers

    @Override
    public Story findStoryByTaskIndex(int taskIndex) {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new IllegalArgumentException(INVALID_TASK_INDEX_MSG);
        }
        return stories.get(tasks.size() - taskIndex - 1);
    }

    @Override
    public TaskType validateTaskTypeStory(TaskType type, int taskIndex) {
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
        return feedbacks.get(tasks.size() - taskIndex - 1);
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
    public Feedback findFeedbackByIndex(int feedbackIndex) {
        Feedback feedback = feedbacks
                .stream()
                .filter((f -> f.getFeedbacks().get(feedbackIndex).equals(feedbackIndex)))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("No such feedback with index %d", feedbackIndex)));

        return feedback;
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
    public int getNextId() {
        return this.nextId;
    }
}

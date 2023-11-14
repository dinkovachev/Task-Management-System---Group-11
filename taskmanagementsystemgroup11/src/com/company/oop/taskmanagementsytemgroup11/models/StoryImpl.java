package com.company.oop.taskmanagementsytemgroup11.models;

import com.company.oop.taskmanagementsytemgroup11.models.contracts.Comment;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Members;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Story;
import com.company.oop.taskmanagementsytemgroup11.models.contracts.Task;
import com.company.oop.taskmanagementsytemgroup11.models.enums.Priority;
import com.company.oop.taskmanagementsytemgroup11.models.enums.Size;
import com.company.oop.taskmanagementsytemgroup11.models.enums.Status;

import java.util.List;

import static java.lang.String.format;

public class StoryImpl extends TaskImpl implements Story {
    private Priority priority;
    private Size size;
    private Status status;
    private String assignee;

    // TODO Georgi Q: Assignee to be added.

    public StoryImpl(int id, String title, String description, Priority priority, Size size, String assignee)
    // TODO: How do we get the members?
    {
        super(id, title, description);
        setPriority(priority);
        setSize(size);
        setAssignee(assignee);
        this.status = Status.NOT_DONE;
    }

    @Override
    public Priority getPriority() {
        return priority;
    }

    @Override
    public Size getSize() {
        return size;
    }

    @Override
    public Status getStatus() {
        return status;
    }

    @Override
    public String getMembers() {
        return assignee;
    }

    private void setPriority(Priority priority) {
        this.priority = priority;
    }

    private void setSize(Size size) {
        this.size = size;
    }

    private void setStatus(Status status) {
        this.status = status;
    }

    private void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public void advanceStatus() {
        if (getStatus() == Status.NOT_DONE) {
            setStatus(Status.IN_PROGRESS);
            System.out.println("Story status set to InProgress.");
        } else if (getStatus() == Status.IN_PROGRESS) {
            setStatus(Status.DONE);
            System.out.println("Story status set to Done.");
        } else {
            System.out.println("Current story status is already Done.");
        }
    }

    //ToDo
    // Finish the override methods from the interfaces
    @Override
    public List<Task> getAllTasks() {
        return null;
    }

    //ToDo
    // Finish the override methods from the interfaces
    @Override
    public void addComment(Comment comment) {

    }

    public void revertStatus() {
        if (getStatus() == Status.DONE) {
            setStatus(Status.IN_PROGRESS);
            System.out.println("Story status set to InProgress.");
        } else if (getStatus() == Status.IN_PROGRESS) {
            setStatus(Status.NOT_DONE);
            System.out.println("Story status set to Not Done.");
        } else {
            System.out.println("Current story status is already Not Done.");
        }
    }

    @Override
    public String getAsString() {
        return null;
    }
}
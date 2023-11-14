package com.company.oop.taskmanagementsytemgroup11.models.contracts;

import com.company.oop.taskmanagementsytemgroup11.models.enums.Priority;
import com.company.oop.taskmanagementsytemgroup11.models.enums.Size;
import com.company.oop.taskmanagementsytemgroup11.models.enums.Status;

import java.util.List;

public interface Story extends Task {

    public Priority getPriority();

    public Size getSize();

    public Status getStatus();

    public String getMembers();

    public List<Story> getStories();

    void changePriority(Priority priority);
}

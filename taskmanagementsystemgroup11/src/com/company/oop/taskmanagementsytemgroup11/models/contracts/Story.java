package com.company.oop.taskmanagementsytemgroup11.models.contracts;

import com.company.oop.taskmanagementsytemgroup11.models.enums.Priority;
import com.company.oop.taskmanagementsytemgroup11.models.enums.Size;
import com.company.oop.taskmanagementsytemgroup11.models.enums.Status;

import java.util.List;
import java.util.Map;

public interface Story extends Task {

    Priority getPriority();

    String getMembers();

    List<Story> getStories();

    List<Feedback> getFeedbacks();

    void changePriority(Priority priority);

    void changeSize(Size size);

    Size getSize();
}

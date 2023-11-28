package com.company.oop.taskmanagementsytemgroup11.models.contracts;

import com.company.oop.taskmanagementsytemgroup11.models.enums.Priority;
import com.company.oop.taskmanagementsytemgroup11.models.enums.Size;

import java.util.List;

public interface Story extends Task {

    Priority getPriority();

    String getMembers();

    void changePriority(int taskIndex, Priority priority);

    void changeSize(int taskIndex, Size size);

    Size getSize();
}

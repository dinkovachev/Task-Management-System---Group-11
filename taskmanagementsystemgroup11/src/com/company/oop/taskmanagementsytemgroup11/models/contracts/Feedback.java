package com.company.oop.taskmanagementsytemgroup11.models.contracts;

import java.util.List;

public interface Feedback extends Task {

    int getRating();

    void changeRating(int rating);

    List<Feedback> getFeedbacks();
}
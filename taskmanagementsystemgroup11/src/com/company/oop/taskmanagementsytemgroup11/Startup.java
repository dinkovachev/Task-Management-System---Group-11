package com.company.oop.taskmanagementsytemgroup11;

import com.company.oop.taskmanagementsytemgroup11.core.TaskManagementSystemEngineImpl;

public class Startup {
    public static void main(String[] args) {
        TaskManagementSystemEngineImpl engine = new TaskManagementSystemEngineImpl();
        engine.start();
    }
}

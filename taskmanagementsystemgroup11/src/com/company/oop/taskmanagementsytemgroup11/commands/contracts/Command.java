package com.company.oop.taskmanagementsytemgroup11.commands.contracts;

import java.util.List;

public interface Command {

    String execute(List<String> parameters);

}

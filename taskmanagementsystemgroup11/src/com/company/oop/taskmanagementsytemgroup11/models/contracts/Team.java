package com.company.oop.taskmanagementsytemgroup11.models.contracts;

import java.util.List;

public interface Team extends Printable {

    String getName();

    List<Members> getMembers();

    void addMember(Members member);


}

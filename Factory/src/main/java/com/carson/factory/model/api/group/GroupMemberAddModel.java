package com.carson.factory.model.api.group;

import java.util.HashSet;
import java.util.Set;

/**
 * Author: Create by Carson on 2021/1/18
 */
public class GroupMemberAddModel {
    private Set<String> users = new HashSet<>();

    public GroupMemberAddModel(Set<String> users) {
        this.users = users;
    }

    public Set<String> getUsers() {
        return users;
    }

    public void setUsers(Set<String> users) {
        this.users = users;
    }
}

package com.example.myapplication.dto;

import java.util.List;

public class BulkLoadUserRequest {

    List<UserDto> users;

    public BulkLoadUserRequest(List<UserDto> users) {
        this.users = users;
    }

    public List<UserDto> getUsers() {
        return users;
    }

    public void setUsers(List<UserDto> users) {
        this.users = users;
    }
}

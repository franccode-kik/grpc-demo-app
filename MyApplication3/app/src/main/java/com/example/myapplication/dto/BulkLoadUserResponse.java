package com.example.myapplication.dto;

import java.util.List;

public class BulkLoadUserResponse {
    List<CreatedUserDto> createdUsers;

    public BulkLoadUserResponse(List<CreatedUserDto> createdUsers) {
        this.createdUsers = createdUsers;
    }

    public List<CreatedUserDto> getCreatedUsers() {
        return createdUsers;
    }

    public void setCreatedUsers(List<CreatedUserDto> createdUsers) {
        this.createdUsers = createdUsers;
    }
}

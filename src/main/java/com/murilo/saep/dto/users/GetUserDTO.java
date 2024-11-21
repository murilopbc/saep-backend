package com.murilo.saep.dto.users;

import com.murilo.saep.entities.User;

public record GetUserDTO(Long id, String name) {
    public GetUserDTO(User user){
        this(user.getId_user(), user.getName());
    }
}

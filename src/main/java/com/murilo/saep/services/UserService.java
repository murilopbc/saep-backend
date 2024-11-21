package com.murilo.saep.services;

import com.murilo.saep.dto.users.GetUserDTO;
import com.murilo.saep.dto.users.PostUserDTO;

import java.util.List;

public interface UserService {

    GetUserDTO createUser(PostUserDTO data);
    List<GetUserDTO> getAllUsers();
    GetUserDTO getUserById(Long id);
}

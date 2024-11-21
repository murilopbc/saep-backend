package com.murilo.saep.serviceImpl;

import com.murilo.saep.dto.users.GetUserDTO;
import com.murilo.saep.dto.users.PostUserDTO;
import com.murilo.saep.entities.User;
import com.murilo.saep.exceptions.NotFoundException;
import com.murilo.saep.repositories.UserRepository;
import com.murilo.saep.services.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;



    @Override
    @Transactional
    public GetUserDTO createUser(PostUserDTO data) {

        if (userRepository.findByEmail(data.email()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User already exists");
        }

        User user = new User(data);
        this.userRepository.save(user);

        return new GetUserDTO(user);
    }


    @Override
    public List<GetUserDTO> getAllUsers() {
        return this.userRepository.findAll().stream().map(GetUserDTO::new).toList();
    }


    @Override
    public GetUserDTO getUserById(Long id) {
        return new GetUserDTO(this.userRepository.findById(id).orElseThrow(() -> new NotFoundException("Local not found!")));
    }



}

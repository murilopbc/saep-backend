package com.murilo.saep.controllers;

import com.murilo.saep.dto.users.GetUserDTO;
import com.murilo.saep.dto.users.PostUserDTO;
import com.murilo.saep.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {


    @Autowired
    private UserService userService;


    @PostMapping
    public ResponseEntity<GetUserDTO> createUser(@RequestBody @Valid PostUserDTO data, UriComponentsBuilder uriBuilder){

        GetUserDTO user = userService.createUser(data);

        var uri = uriBuilder.path("/api/users/{id}").buildAndExpand(user.id()).toUri();

        return ResponseEntity.created(uri).body(user);
    }

    @GetMapping
    public ResponseEntity <List<GetUserDTO>> getAllUsers(){
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetUserDTO> getUserById(@PathVariable Long id) {

        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

}

package com.murilo.saep.entities;

import com.murilo.saep.dto.users.PostUserDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "users")
@Entity(name = "users")
@NoArgsConstructor
@Getter
@AllArgsConstructor
@EqualsAndHashCode(of = "id_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_user;

    private String name;

    private String email;

    private String password;


    public User(PostUserDTO data){
        this.name = data.name();
        this.email = data.email();
        this.password = data.password();
    }

}

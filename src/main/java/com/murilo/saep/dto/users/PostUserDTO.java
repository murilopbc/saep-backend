package com.murilo.saep.dto.users;

import jakarta.validation.constraints.Email;

public record PostUserDTO(String name, @Email String email, String password) {
}

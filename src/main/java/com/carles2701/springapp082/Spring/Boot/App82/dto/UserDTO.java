package com.carles2701.springapp082.Spring.Boot.App82.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserDTO {

    private Long id;

    private String password;

    private String username;

    private String name;

    private String email;

    private String surname;

    private LocalDate dateOfBirth;

    public UserDTO() {
    }

}


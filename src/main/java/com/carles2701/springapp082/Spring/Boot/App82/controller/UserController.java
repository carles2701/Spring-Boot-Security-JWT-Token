package com.carles2701.springapp082.Spring.Boot.App82.controller;

import com.carles2701.springapp082.Spring.Boot.App82.dto.UserDTO;
import com.carles2701.springapp082.Spring.Boot.App82.exception.NotFoundException;
import com.carles2701.springapp082.Spring.Boot.App82.model.User;
import com.carles2701.springapp082.Spring.Boot.App82.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/save")
    public User saveUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable("id") Long id) throws NotFoundException {
        return userService.getUserById(id);
    }

    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @PutMapping("/{id}")
    public UserDTO updateUser(@RequestBody UserDTO userDTO, @PathVariable("id") Long id) throws NotFoundException {
        return userService.updateUser(id, userDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(
            @PathVariable("id") Long id) {
        userService.deleteUserById(id);
    }

    @PutMapping("/{userId}/role/{roleId}")
    public void addRoleToUser(@PathVariable("userId") Long userId, @PathVariable("roleId") Long roleId) throws NotFoundException {
        userService.addRoleToUser(userId, roleId);
    }

}

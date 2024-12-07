package com.jpa.postgress.dboperations.controller;

import com.jpa.postgress.dboperations.entity.Users;
import com.jpa.postgress.dboperations.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    private UsersService usersService;

    @GetMapping
    public List<Users> getUsers() {
        return usersService.getUsers();
    }

}

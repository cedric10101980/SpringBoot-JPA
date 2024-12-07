package com.jpa.postgress.dboperations.service;

import com.jpa.postgress.dboperations.entity.Users;
import com.jpa.postgress.dboperations.repo.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService {
    @Autowired
    private UsersRepo usersRepo;

    public List<Users> getUsers() {
        return usersRepo.findAll();
    }
}

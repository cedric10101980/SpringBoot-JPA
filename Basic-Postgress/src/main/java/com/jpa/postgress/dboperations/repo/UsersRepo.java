package com.jpa.postgress.dboperations.repo;

import com.jpa.postgress.dboperations.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepo extends JpaRepository<Users, Long> {
}

package com.arch.tests.mvc.repository;

import com.arch.tests.mvc.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}

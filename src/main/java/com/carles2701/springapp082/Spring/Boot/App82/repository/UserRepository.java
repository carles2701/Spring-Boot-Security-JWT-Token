package com.carles2701.springapp082.Spring.Boot.App82.repository;

import com.carles2701.springapp082.Spring.Boot.App82.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}

package com.carles2701.springapp082.Spring.Boot.App82.repository;

import com.carles2701.springapp082.Spring.Boot.App82.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}

package com.carles2701.springapp082.Spring.Boot.App82.controller;

import com.carles2701.springapp082.Spring.Boot.App82.dto.RoleDTO;
import com.carles2701.springapp082.Spring.Boot.App82.exception.NotFoundException;
import com.carles2701.springapp082.Spring.Boot.App82.model.Role;
import com.carles2701.springapp082.Spring.Boot.App82.model.User;
import com.carles2701.springapp082.Spring.Boot.App82.service.RoleService;
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
@RequestMapping(path = "/roles", produces = MediaType.APPLICATION_JSON_VALUE)
public class RoleController {

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public List<RoleDTO> getAllRoles() {
        return roleService.getAllRoles();
    }

    @GetMapping("/{id}")
    public Role getRoleById(@PathVariable("id") Long id) throws NotFoundException {
        return roleService.getRoleById(id);
    }

    @PutMapping("/{id}")
    public RoleDTO updateRole(@PathVariable("id") Long id,
                                           @RequestBody RoleDTO roleDTO) throws NotFoundException {
        return roleService.updateRole(id,roleDTO);
    }
    @PostMapping("/add")
    public RoleDTO addRole(@RequestBody RoleDTO roleDTO){
        return roleService.addRole(roleDTO);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteRole(@PathVariable Long id){
        roleService.deleteRoleById(id);
    }

}

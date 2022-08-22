package com.carles2701.springapp082.Spring.Boot.App82.service;

import com.carles2701.springapp082.Spring.Boot.App82.dto.RoleDTO;
import com.carles2701.springapp082.Spring.Boot.App82.exception.NotFoundException;
import com.carles2701.springapp082.Spring.Boot.App82.model.Role;
import com.carles2701.springapp082.Spring.Boot.App82.model.User;
import com.carles2701.springapp082.Spring.Boot.App82.repository.RoleRepository;
import com.carles2701.springapp082.Spring.Boot.App82.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleService{

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    public RoleDTO entityToDto(Role role) {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setName(role.getName());
        return roleDTO;

    }

    public Role dtoToEntity(RoleDTO roleDTO) {
        Role role = new Role();
        role.setName(roleDTO.getName());
        return role;
    }

    public List<RoleDTO> getAllRoles(){
        return roleRepository
                .findAll()
                .stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    public Role getRoleById(Long id) throws NotFoundException{
        return roleRepository.findById(id)
                .orElseThrow(() ->  new NotFoundException("Role not found"));
    }

    public RoleDTO updateRole(Long id, RoleDTO roleDTO) throws NotFoundException {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Role not found"));
        role.setName(roleDTO.getName());
        roleRepository.save(role);

        return roleDTO;
    }
/*
    public void addExperienceByUserId(Long id, Long userId) throws NotFoundException {
        Experience experience = experienceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Experience not found"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found"));
        experience.getUsersWithExperience().add(user);

        experienceRepository.save(experience);

    }

 */

    public RoleDTO addRole(RoleDTO roleDTO) {
        Role role = this.dtoToEntity(roleDTO);
        roleRepository.save(role);
        return roleDTO;
    }

    public void deleteRoleById(Long id) {
        roleRepository.deleteById(id);

    }

}

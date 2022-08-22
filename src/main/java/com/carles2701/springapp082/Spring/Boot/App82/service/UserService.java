package com.carles2701.springapp082.Spring.Boot.App82.service;

import com.carles2701.springapp082.Spring.Boot.App82.dto.UserDTO;
import com.carles2701.springapp082.Spring.Boot.App82.exception.NotFoundException;
import com.carles2701.springapp082.Spring.Boot.App82.model.Role;
import com.carles2701.springapp082.Spring.Boot.App82.model.User;
import com.carles2701.springapp082.Spring.Boot.App82.repository.RoleRepository;
import com.carles2701.springapp082.Spring.Boot.App82.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null){
            log.error("User not found in the database");
            throw new UsernameNotFoundException("User not found");
        }
        else {
            log.info("User found in the database: {}", username);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

        user.getRoles()
                .forEach(role -> authorities
                        .add(new SimpleGrantedAuthority
                                (
                                role.getName()
                                )));

        return new org
                .springframework
                .security
                .core
                .userdetails
                .User(
                        user.getUsername(),
                        user.getPassword(),
                        authorities);
    }

    public UserDTO entityToDto(User user) {

        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(user.getEmail());
        userDTO.setName(user.getName());
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        userDTO.setSurname(user.getSurname());
        userDTO.setDateOfBirth(user.getDateOfBirth());

        return userDTO;

    }

    public User dtoToEntity(UserDTO userDTO) {

        User user = new User();
        user.setName(userDTO.getName());
        user.setSurname(userDTO.getSurname());
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());
        user.setDateOfBirth(userDTO.getDateOfBirth());
        user.setUsername(userDTO.getUsername());

        return user;
    }

    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }


    public User getUserById(Long id) throws NotFoundException{
        return userRepository.findById(id)
                .orElseThrow(() ->  new NotFoundException("User not found"));
    }

    public List<UserDTO> getAllUsers() {
        return userRepository
                .findAll()
                .stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }


    public void addRoleToUser(Long userId, Long roleId) throws NotFoundException {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new NotFoundException("Experience not found"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found"));
        role.getUsers().add(user);

         roleRepository.save(role);
    }

    public UserDTO updateUser(Long id, UserDTO userDTO) throws NotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));
        user.setEmail(userDTO.getEmail());
        user.setUsername(userDTO.getUsername());
        user.setName(userDTO.getName());
        user.setSurname(user.getSurname());
        user.setDateOfBirth(userDTO.getDateOfBirth());
        user.setPassword(userDTO.getPassword());
        userRepository.save(user);

        return userDTO;
    }

}

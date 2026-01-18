package com.codiecode.userinfo.service;

import com.codiecode.common.dto.UserDTO;
import com.codiecode.common.exception.ResourceNotFoundException;
import com.codiecode.userinfo.entity.User;
import com.codiecode.userinfo.repo.UserRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public List<UserDTO> findAllUsers() {
        List<User> users = userRepo.findAll();
        return users.stream()
                .map(user -> new UserDTO(
                        user.getUserId(),
                        user.getUserName(),
                        user.getUserPassword(),
                        user.getCity(),
                        user.getAddress()
                ))
                .toList();
    }

    public UserDTO addUser(UserDTO user) {
        User userToAdd = new User(
                user.getUserName(),
                user.getUserPassword(),
                user.getCity(),
                user.getAddress()
        );
        User addedUser = userRepo.save(userToAdd);
        return new UserDTO(
                addedUser.getUserId(),
                addedUser.getUserName(),
                addedUser.getUserPassword(),
                user.getCity(),
                user.getAddress()
        );
    }

    public UserDTO fetchUserById(Long id) {
        return userRepo.findById(id)
                .map(user -> new UserDTO(
                        user.getUserId(),
                        user.getUserName(),
                        user.getUserPassword(),
                        user.getCity(),
                        user.getAddress()
                )).orElseThrow(
                        () -> new ResourceNotFoundException("User not found with userId: " + id));
    }
}

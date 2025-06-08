package com.ankilla.userinfo.service;

import com.ankilla.userinfo.dto.UserDTO;
import com.ankilla.userinfo.entity.User;
import com.ankilla.userinfo.mapper.UserMapper;
import com.ankilla.userinfo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;
    private UserMapper userMapper;

    public UserService(UserMapper userMapper){
        this.userMapper=userMapper;
    }

    public List<UserDTO> findAllUsers() {

        List<User> allUsers = userRepo.findAll();
        return allUsers.stream().map(user -> userMapper.mapUserToUserDTO(user)).toList();
    }

    public UserDTO addUser(UserDTO userDTO) {
        User user = userRepo.save(userMapper.mapUserDTOToUser(userDTO));
        return userMapper.mapUserToUserDTO(user);
    }

    public ResponseEntity<UserDTO> findById(Integer id) {
        Optional<User> user = userRepo.findById(id);;
        return user.map(value -> new ResponseEntity<>(userMapper.mapUserToUserDTO(value), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));

    }
}

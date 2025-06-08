package com.ankilla.userinfo.controller;

import com.ankilla.userinfo.dto.UserDTO;
import com.ankilla.userinfo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

//    @Autowired
    UserService userService;

    public UserController(UserService userService){
        this.userService =userService;
    }

    @GetMapping("/fetchAllUsers")
    public ResponseEntity<List<UserDTO>> fetAllRestaurants(){
        List<UserDTO> allUsers = userService.findAllUsers();
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }


    @PostMapping(value = "/addUser")
    public ResponseEntity<UserDTO> saveRestaurant(@RequestBody UserDTO userDTO){

        System.out.println("----payload " + userDTO);


        UserDTO userAdded  = userService.addUser(userDTO);
        return new ResponseEntity<>(userAdded, HttpStatus.CREATED);
    }

    @GetMapping("fetchById/{id}")
    public ResponseEntity<UserDTO> findUserById(@PathVariable Integer id){
      return  userService.findById(id);
    }


}

package com.backend.crud.controller;

import com.backend.crud.model.User;
import com.backend.crud.serviceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api/auth")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/")
    public ResponseEntity<Map<String, Object>> getAllUsers(){
        Map<String,Object> res = new HashMap<>();
        if(userService.getAllUsers().isEmpty()){
            res.put("status", HttpStatus.NOT_FOUND.value());
            res.put("message","No Data Found!");
        } else {
            res.put("status", HttpStatus.OK.value());
            res.put("message","Data Fetch Successfully!");
            res.put("data",userService.getAllUsers());
        }
        return new ResponseEntity<>(res,HttpStatus.OK);
    }

    @PostMapping("/store")
    public ResponseEntity<Map<String,Object>> createUser(@RequestBody User user){
        Map<String,Object> res = new HashMap<>();
        User usr = new User();
        usr.setName(user.getName());
        usr.setEmail(user.getEmail());
        usr.setMobile(user.getMobile());
        res.put("status", HttpStatus.OK.value());
        res.put("message","Data Created Successfully!");
        res.put("data",userService.createUser(usr));
        return new ResponseEntity<>(res,HttpStatus.OK);
    }

    @GetMapping("/edit")
    public ResponseEntity<Map<String,Object>> getUserById(@RequestParam("id") String id){
        Map<String,Object> res = new HashMap<>();
        Optional<User> usr = userService.getUserById(id);
        if(usr.isEmpty()){
            res.put("status", HttpStatus.NOT_FOUND.value());
            res.put("message","No Data Found!");
        } else {
            res.put("status", HttpStatus.OK.value());
            res.put("message","Data Fetch Successfully!");
            res.put("data",usr);
        }
        return new ResponseEntity<>(res,HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Map<String,Object>> updateUserById(@RequestParam("id") String id,@RequestBody User user){
        Map<String,Object> res = new HashMap<>();
        User usr = userService.updateUserById(user,id);
        if(usr==null){
            res.put("status", HttpStatus.BAD_REQUEST.value());
            res.put("message","Failed to Update!");
        } else {
            res.put("status", HttpStatus.OK.value());
            res.put("message","Data Stored Successfully!");
            res.put("data",usr);
        }
        return new ResponseEntity<>(res,HttpStatus.OK);
    }

    @DeleteMapping("/del")
    public ResponseEntity<Map<String,Object>> deleteUserById(@RequestParam("id") String id){
        Map<String,Object> res = new HashMap<>();
        boolean b = userService.deleteUserById(id);
        if(b){
            res.put("status", HttpStatus.OK.value());
            res.put("message","Data Delete Successfully!");
        } else {
            res.put("status", HttpStatus.NOT_FOUND.value());
            res.put("message","Failed to Delete!");
        }
        return new ResponseEntity<>(res,HttpStatus.OK);
    }
}

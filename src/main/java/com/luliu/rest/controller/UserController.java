package com.luliu.rest.controller;

import com.luliu.rest.model.User;
import com.luliu.rest.service.IUserService;
import com.luliu.rest.utils.Page;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author luliu3 on 2016/8/4.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private IUserService userService;

    private static org.slf4j.Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping
    public ResponseEntity getAllUser() {

        HttpHeaders headers = new HttpHeaders();
        List<User> list = userService.getAllUser();
        if (null == list) {
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }
        headers.set("Number of Records Found", String.valueOf(list.size()));
        return new ResponseEntity<List>(list, headers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getUser(@PathVariable Long id) {

        HttpHeaders headers = new HttpHeaders();
        User u = userService.getUser(id);
        if (null == u) {
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }
        headers.set("Record Found", u.getFirstname() + " " + u.getLastname());
        return new ResponseEntity<User>(u, headers, HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity newUser(@RequestBody User user) {

        HttpHeaders headers = new HttpHeaders();
        if (null == user){
            return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
        } else if (userService.getUser(user.getId()) != null) {
            headers.set("Record Conflict", "User with ID " + user.getId() + " already exists.");
            return new ResponseEntity<User>(headers, HttpStatus.CONFLICT);
        }
        userService.insertUser(user);
        headers.set("Record Created", user.getFirstname() + " " + user.getLastname());
        return new ResponseEntity<User>(user, headers, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        User user = userService.getUser(id);
        if (null == user){
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        int cnt = userService.deleteUser(id);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Record Deleted", user.getFirstname() + " " + user.getLastname());
        return new ResponseEntity<User>(user, headers, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable Long id, @RequestBody User user) {

        User isExist = userService.getUser(id);
        if (null == isExist) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        } else if (null == user) {
            return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
        }
        userService.updateUser(user);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Record Updated", user.getFirstname() + " " + user.getLastname());
        return new ResponseEntity<User>(user, headers, HttpStatus.OK);
    }

    @RequestMapping("/page")
    public ResponseEntity getUserInRange(@RequestParam int low, @RequestParam int high) {
        Page<User> page = userService.getUserPageInRange(low, high);
        return new ResponseEntity<Page<User>>(page, HttpStatus.OK);
    }
}

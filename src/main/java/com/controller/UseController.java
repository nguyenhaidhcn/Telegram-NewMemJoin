package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.telegram.UserDB;
import com.telegram.UserRepository;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by rajeevkumarsingh on 27/06/17.
 */
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by rajeevkumarsingh on 27/06/17.
 */
@RestController
@RequestMapping("/api")
public class UseController {

    @Autowired
    UserRepository userRepository;


    @PostMapping("/user")
    public UserDB createNote(@Valid @RequestBody UserDB userDB) {

        return userRepository.save(userDB);
    }


    @GetMapping("/user")
    public String getAllNotes() {
        return "test";
    }

}

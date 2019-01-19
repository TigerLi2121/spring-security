package com.mm.demo.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.mm.demo.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lwl
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @PostMapping
    @JsonView(User.SimpleView.class)
    public User create(@Valid @RequestBody User user, BindingResult errors){
        log.info("user:{}", user);
        if (errors.hasErrors()) {
            errors.getAllErrors().stream().forEach(e -> System.out.println(e.getDefaultMessage()));
        }
        return User.builder().id(8L)
                .username(user.getUsername())
                .password(user.getPassword())
                .birthday(user.getBirthday())
                .build();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        log.info("id:{}", id);
    }

    @PutMapping
    @JsonView(User.SimpleView.class)
    public User update(@Valid @RequestBody User user, BindingResult errors){
        log.info("user:{}", user);
        if (errors.hasErrors()) {
            errors.getAllErrors().stream().forEach(e -> System.out.println(e.getDefaultMessage()));
        }
        return User.builder().id(8L)
                .username(user.getUsername())
                .password(user.getPassword())
                .birthday(user.getBirthday())
                .build();
    }

    @GetMapping
    @JsonView(User.SimpleView.class)
    public List<User> list(){
        List<User> users = new ArrayList<>();
        users.add(User.builder().username("aa").build());
        users.add(User.builder().username("bb").build());
        users.add(User.builder().username("cc").build());
        return users;
    }

    @GetMapping("/{id}")
    @JsonView(User.DetailView.class)
    public User get(@PathVariable Long id){
        log.info("id:{}", id);
        return User.builder().username("tom").password("2333").build();
    }
}

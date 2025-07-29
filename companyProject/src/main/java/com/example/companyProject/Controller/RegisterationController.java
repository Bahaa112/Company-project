package com.example.companyProject.Controller;


import com.example.companyProject.Entity.Users;
import com.example.companyProject.Service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RegisterationController {
    private final UsersService usersService;

    @PostMapping("/login")
    public Object login(@RequestBody Users users){
        return usersService.verify(users);
    }

    @PostMapping("/signUp")
    public String addUser(@RequestBody Users users){
        return usersService.addUser(users.getEmail(),users.getName(),users.getPassword(),users.getCompany().getId());
    }
}

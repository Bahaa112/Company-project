package com.example.companyProject.Controller;

import com.example.companyProject.Entity.Company;
import com.example.companyProject.Entity.Users;
import com.example.companyProject.Enums.UserStatus;
import com.example.companyProject.Projection.CompanyProjection;
import com.example.companyProject.Projection.UsersProjection;
import com.example.companyProject.Service.UsersService;
import jdk.jshell.spi.ExecutionControl;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UsersController {

    private final UsersService usersService;

    @GetMapping
    public Page<UsersProjection> getAllUsers(@RequestParam(required = false) String name ,@RequestParam(required = false) UserStatus status,
                                                 Pageable pageable){

        return usersService.getAllUsersBy(name , status,pageable );

    }
    @DeleteMapping("/{id}")
    public void deleteByName(@PathVariable int id){
        usersService.deleteById(id);
    }

    @PutMapping
    public void updateUser(@RequestBody Users user){
        usersService.updateUser(user.getEmail(),user.getName(),user.getPassword());

    }
    @PutMapping("/{id}/{status}")
    public void updateStatus(@PathVariable int id,@PathVariable UserStatus status){
        usersService.updateStatus(id,status);
    }

}

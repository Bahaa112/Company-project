package com.example.companyProject.Controller;


import com.example.companyProject.Entity.Users;
import com.example.companyProject.Enums.UserStatus;
import com.example.companyProject.Projection.UsersProjection;
import com.example.companyProject.Service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UsersController {

    private final UsersService usersService;

    @GetMapping
    public Page<UsersProjection> getAllUsers(@RequestParam(required = false) String name ,@RequestParam(required = false) UserStatus status, Pageable pageable){
        return usersService.getAllUsersBy(name , status,pageable );
    }
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id){
        usersService.deleteById(id);
    }
    @PutMapping
    public void updateUser(@RequestBody Users user){
        usersService.updateUser(user.getEmail(), user.getName(), user.getPassword());
    }
    @PutMapping("/{id}")
    public void updateStatus(@PathVariable int id,@RequestParam UserStatus status){
        usersService.updateStatus(id,status);
    }
}

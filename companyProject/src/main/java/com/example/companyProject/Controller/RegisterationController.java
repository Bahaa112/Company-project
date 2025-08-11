package com.example.companyProject.Controller;


import com.example.companyProject.Entity.Users;
import com.example.companyProject.Security.ChangePassword;
import com.example.companyProject.Service.ForgotPasswordService;
import com.example.companyProject.Service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class RegisterationController {
    private final UsersService usersService;
    private final ForgotPasswordService forgotPasswordService;


    @PostMapping("/login")
    public Object login(@RequestBody Users users){
        return usersService.verify(users);
    }

    @PostMapping("/signUp")
    public String addUser(@RequestBody Users users){
        return usersService.addUser(users.getEmail(),users.getName(),users.getPassword(),users.getCompany().getId());
    }
    @PostMapping("/verify/{email}")
    public ResponseEntity<String> verifyEmail(@PathVariable String email) {
        return forgotPasswordService.verifyEmail(email);
    }

    @PostMapping("/verifyOtp/{otp}/{email}")
    public ResponseEntity<String> verifyOtp(@PathVariable Integer otp, @PathVariable String email) {
        return forgotPasswordService.verifyOtp(otp, email);
    }

    @PostMapping("/changePassword/{otp}/{email}")
    public ResponseEntity<String> changePasswordHandler(@RequestBody ChangePassword changePassword, @PathVariable Integer otp, @PathVariable String email) {
        return forgotPasswordService.changePassword(email, otp, changePassword);
    }
}

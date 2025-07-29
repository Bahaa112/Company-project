package com.example.companyProject.Controller;

import com.example.companyProject.Security.ChangePassword;
import com.example.companyProject.Service.ForgotPasswordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
public class ForgotPasswordController {
    private final ForgotPasswordService forgotPasswordService;

    @PostMapping("/verify/{email}")
    public ResponseEntity<String> verifyEmail(@PathVariable String email) {
        return forgotPasswordService.verifyEmail(email);
    }

    @PostMapping("/verifyOtp/{otp}/{email}")
    public ResponseEntity<String> verifyOtp(@PathVariable Integer otp, @PathVariable String email) {
        return forgotPasswordService.verifyOtp(otp, email);
    }

    @PostMapping("/changePassword/{email}")
    public ResponseEntity<String> changePasswordHandler(@RequestBody ChangePassword changePassword, @PathVariable String email) {
        return forgotPasswordService.changePassword(changePassword, email);
    }
}



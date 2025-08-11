package com.example.companyProject.Feign;

import com.example.companyProject.Security.ChangePassword;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.stereotype.Controller
@RequiredArgsConstructor
@RequestMapping("/auth")

public class Controller {
    private final ForgotPasswordServiceFeign forgotPasswordServiceFeign;

    @PostMapping("/feign/verify/{email}")
    public ResponseEntity<String> verifyEmail(@PathVariable String email) {
        return forgotPasswordServiceFeign.verifyEmail(email);
    }

    @PostMapping("/feign/verifyOtp/{otp}/{email}")
    public ResponseEntity<String> verifyOtp(@PathVariable Integer otp, @PathVariable String email) {
        return forgotPasswordServiceFeign.verifyOtp(otp, email);
    }

    @PostMapping("/feign/changePassword/{otp}/{email}")
    public ResponseEntity<String> changePassword(@RequestBody ChangePassword changePassword,
                                                 @PathVariable Integer otp,
                                                 @PathVariable String email) {
        return forgotPasswordServiceFeign.changePassword(email, otp, changePassword);
    }
}

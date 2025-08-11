package com.example.companyProject.Feign;

import com.example.companyProject.Security.ChangePassword;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "forgot-password-service", url = "http://localhost:8082")
public interface ForgotPasswordClient {

    @PostMapping("/auth/verify/{email}")
    ResponseEntity<String> verifyEmail(@PathVariable("email") String email);

    @PostMapping("/auth/verifyOtp/{otp}/{email}")
    ResponseEntity<String> verifyOtp(@PathVariable("otp") Integer otp, @PathVariable("email") String email);

    @PostMapping("/auth/changePassword/{otp}/{email}")
    ResponseEntity<String> changePassword(@RequestBody ChangePassword changePassword,
                                          @PathVariable("otp") Integer otp,
                                          @PathVariable("email") String email);
}

package com.example.companyProject.Feign;

import com.example.companyProject.Security.ChangePassword;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ForgotPasswordServiceFeign {

    private final ForgotPasswordClient forgotPasswordClient;

    public ResponseEntity<String> verifyEmail(String email) {
        return forgotPasswordClient.verifyEmail(email);
    }

    public ResponseEntity<String> verifyOtp(Integer otp, String email) {
        return forgotPasswordClient.verifyOtp(otp, email);
    }

    public ResponseEntity<String> changePassword(String email, Integer otp, ChangePassword changePassword) {
        return forgotPasswordClient.changePassword(changePassword, otp, email);
    }
}

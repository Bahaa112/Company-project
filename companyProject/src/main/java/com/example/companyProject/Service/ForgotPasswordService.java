package com.example.companyProject.Service;


import com.example.companyProject.DTO.MailBody;
import com.example.companyProject.Entity.ForgotPassword;
import com.example.companyProject.Entity.Users;
import com.example.companyProject.Repository.ForgotPasswordRepository;
import com.example.companyProject.Repository.UsersRepository;
import com.example.companyProject.Security.ChangePassword;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Random;

@Service
public class ForgotPasswordService {

    private final RestTemplate restTemplate;


    private static final String SYSTEM2_BASE_URL = "http://localhost:8082/auth";

    @Autowired
    public ForgotPasswordService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public ResponseEntity<String> verifyEmail(String email) {
        String url = SYSTEM2_BASE_URL + "/verify/" + email;
        return restTemplate.postForEntity(url, null, String.class);
    }

    public ResponseEntity<String> verifyOtp(Integer otp, String email) {
        String url = SYSTEM2_BASE_URL + "/verifyOtp/" + otp + "/" + email;
        return restTemplate.postForEntity(url, null, String.class);
    }

    public ResponseEntity<String> changePassword(String email, Integer otp, ChangePassword changePassword) {
        String url = SYSTEM2_BASE_URL + "/changePassword/" + otp + "/" + email;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<ChangePassword> requestEntity = new HttpEntity<>(changePassword, headers);

        return restTemplate.postForEntity(url, requestEntity, String.class);
    }
}

package com.example.companyProject.Service;


import com.example.companyProject.DTO.MailBody;
import com.example.companyProject.Entity.ForgotPassword;
import com.example.companyProject.Entity.Users;
import com.example.companyProject.Repository.ForgotPasswordRepository;
import com.example.companyProject.Repository.UsersRepository;
import com.example.companyProject.Security.ChangePassword;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class ForgotPasswordService {

    private final UsersRepository usersRepository;
    private final EmailService emailService;
    private final ForgotPasswordRepository forgotPasswordRepository;
    private final PasswordEncoder passwordEncoder;

    public ResponseEntity<String> verifyEmail(String email) {
        Users user = usersRepository.findUsersByEmail(email);
        int otp = otpGenerator();

        MailBody mailBody = MailBody.builder()
                .to(email)
                .text("This is the OTP for your forgot password request: " + otp)
                .subject("OTP for forgot password request")
                .build();

        ForgotPassword forgotPassword = ForgotPassword.builder()
                .otp(otp)
                .expirationTime(new Date(System.currentTimeMillis() + 70 * 1000000L))
                .users(user)
                .build();

        emailService.sendSimpleMessage(mailBody);
        forgotPasswordRepository.save(forgotPassword);
        return ResponseEntity.ok("Email sent for verification");
    }

    public ResponseEntity<String> verifyOtp(Integer otp, String email) {
        Users users = usersRepository.findUsersByEmail(email);
        List<ForgotPassword> forgotPasswords = forgotPasswordRepository.findByOtpAndUsers(otp, users);

        if (forgotPasswords.isEmpty()) {
            return new ResponseEntity<>("Invalid OTP", HttpStatus.NOT_FOUND);
        }

        ForgotPassword forgotPassword = forgotPasswords.get(forgotPasswords.size() - 1);

        if (forgotPassword.getExpirationTime().before(Date.from(Instant.now()))) {
            forgotPasswordRepository.deleteById(forgotPassword.getFpid());
            return new ResponseEntity<>("OTP has expired", HttpStatus.EXPECTATION_FAILED);
        }

        return ResponseEntity.ok("OTP Verified!");
    }

    public ResponseEntity<String> changePassword(ChangePassword changePassword, String email) {
        if (!Objects.equals(changePassword.getPassword(), changePassword.getRepeatPassword())) {
            return new ResponseEntity<>("Please Enter The Password again!", HttpStatus.EXPECTATION_FAILED);
        }

        String encodedPassword = passwordEncoder.encode(changePassword.getPassword());
        usersRepository.updatePassword(email, encodedPassword);

        return ResponseEntity.ok("Password has been changed!");
    }

    private Integer otpGenerator() {
        Random random = new Random();
        return random.nextInt(100000, 999999);
    }
}

package com.example.companyProject.Controller;

/*
import com.example.companyProject.DTO.MailBody;
import com.example.companyProject.Entity.ForgotPassword;
import com.example.companyProject.Entity.Users;
import com.example.companyProject.Repository.ForgotPasswordRepository;
import com.example.companyProject.Repository.UsersRepository;
import com.example.companyProject.Service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Date;
import java.util.Random;

@RestController
@RequestMapping("/forgotPassword")
@RequiredArgsConstructor
public class ForgotPasswordController {

    private final UsersRepository usersRepository;
    private final EmailService emailService;
    private final ForgotPasswordRepository forgotPasswordRepository;


    @PostMapping("/verifyMail/{email}")
    public ResponseEntity<String> verifyEmail(@PathVariable String email){
        Users user = usersRepository.findUsersByName(email);
        int otp = otpGenerator();
        MailBody mailBody = MailBody.builder().to(email).text("This is the otp for your forgot password request" +otp).subject("OTP for forgot password request").build();

        ForgotPassword forgotPassword = ForgotPassword.builder().otp(otp).expirationTime(new Date(System.currentTimeMillis()+70*1000)).users(user).build();
        emailService.sendSimpleMessage(mailBody);
        forgotPasswordRepository.save(forgotPassword);
        return ResponseEntity.ok("Email sent for verification");

    }
    @PostMapping("/verifyOtp/{otp}/{email}")
    public ResponseEntity<String> verifyOtp(@PathVariable Integer otp , @PathVariable String email){
        Users users = usersRepository.findUsersByEmail(email);
        forgotPasswordRepository.findByOtpAndUsers(otp,users);
        ForgotPassword forgotPassword = (ForgotPassword) forgotPasswordRepository.findByOtpAndUsers(otp,users);

        if(forgotPassword.getExpirationTime().before(Date.from(Instant.now()))){
            forgotPasswordRepository.deleteById(forgotPassword.getFpid());
            return new ResponseEntity<>("OTP has expired", HttpStatus.EXPECTATION_FAILED);
        }

    }

    private Integer otpGenerator(){
        Random random = new Random();
        return random.nextInt(100_000,999_999);
    }



}
*/


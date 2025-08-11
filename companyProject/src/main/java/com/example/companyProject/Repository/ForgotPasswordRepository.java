package com.example.companyProject.Repository;


import com.example.companyProject.Entity.ForgotPassword;
import com.example.companyProject.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ForgotPasswordRepository extends JpaRepository<ForgotPassword,Integer> {

    List<ForgotPassword> findByOtpAndUsers(Integer otp, Users users);
    List<ForgotPassword> findByUsers(Users users);
}



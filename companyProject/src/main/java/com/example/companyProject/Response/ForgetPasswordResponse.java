package com.example.companyProject.Response;

import lombok.Data;

import java.util.Date;

@Data
public class ForgetPasswordResponse {

    private Integer fpid;
    private Integer otp;
    private Date expirationTime;
}

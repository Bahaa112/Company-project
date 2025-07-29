package com.example.companyProject.DTO;

import com.example.companyProject.Enums.UserStatus;

public class UserDTO {

    private String email;
    private String name;
    private UserStatus status;
    private CompanyDTO company;

    public UserDTO(String email, String name, UserStatus status, CompanyDTO company) {
        this.email = email;
        this.name = name;
        this.status = status;
        this.company = company;
    }
}

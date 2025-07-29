package com.example.companyProject.DTO;


import com.example.companyProject.Entity.Company;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@AllArgsConstructor
@Data
public class LoginReturnDTO {

    private String token;
    private String name;
    private String email;
    private int id;
    private int companyId;
    private Date creationDate;
    private Date updatedDate;

}

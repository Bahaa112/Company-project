package com.example.companyProject.Projection;

import com.example.companyProject.Entity.Users;
import com.example.companyProject.Enums.CompanyStatus;

import java.util.List;

public interface CompanyProjection {

    String getEmail();
    String getCompanyName();
    String getLocation();
    String getId();
    String getUpdatedDate();
    String getCreationDate();
    CompanyStatus getStatus();
    List<UsersProjection> getUsers();
    Integer getApprovedUsersCount();
    Integer getRejectedUsersCount();


}

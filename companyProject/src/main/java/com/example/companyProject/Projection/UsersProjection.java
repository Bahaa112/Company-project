package com.example.companyProject.Projection;

import com.example.companyProject.Enums.UserStatus;

public interface UsersProjection {
    String getEmail();
    String getName();
    CompanyIdOnly getCompany();
    //String getStatus();
    UserStatus getStatus();

     interface CompanyIdOnly
     { Integer getId();

}


}

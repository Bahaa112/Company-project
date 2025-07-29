package com.example.companyProject.Projection;

import java.util.List;

public interface CompanyListAndusers {
    Integer getCompanyid();
    String getCompanyname();
    String getCompanyemail();
    String getCompanylocation();

    List<UsersProjection> getUser();
}

package com.example.companyProject.Controller.Interfaces;

import com.example.companyProject.Projection.CompanyProjection;
import com.example.companyProject.Projection.UsersProjection;

import java.util.List;

public interface UsersControllerInterface {

    public abstract List<UsersProjection> getAllCompanies();
}

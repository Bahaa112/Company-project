package com.example.companyProject.Controller.Interfaces;

import com.example.companyProject.Entity.Company;
import com.example.companyProject.Projection.CompanyProjection;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface CompanyControllerInterface {

    public abstract String addCompany(Company company);
    public abstract String updateCompany(Company company);
}

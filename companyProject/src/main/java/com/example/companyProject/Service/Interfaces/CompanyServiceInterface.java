package com.example.companyProject.Service.Interfaces;

import com.example.companyProject.Entity.Company;
import com.example.companyProject.Projection.CompanyProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CompanyServiceInterface {

     public Page<CompanyProjection> getAllCompanies(Pageable pageable);

    //public abstract void addCompany(Company company);
    public abstract String addCompany(String email,String companyName , String location);
    public abstract String updateCompany(String email,String companyName , String location);
    public abstract CompanyProjection searchCompaniesByCompanyName(String companyName);
    public abstract CompanyProjection updatecompanyByName(String companyName , Company company);
    public abstract Page<CompanyProjection> getCompanies(String name, Pageable pageable);
}

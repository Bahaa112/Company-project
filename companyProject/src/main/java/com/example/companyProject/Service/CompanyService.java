package com.example.companyProject.Service;

import com.example.companyProject.Controller.Exception.BadRequestException;
import com.example.companyProject.Entity.Company;
import com.example.companyProject.Entity.MyUserDetails;
import com.example.companyProject.Enums.CompanyStatus;
import com.example.companyProject.Enums.UserStatus;
import com.example.companyProject.Projection.*;
import com.example.companyProject.Repository.CompanyRepository;
import com.example.companyProject.Repository.UsersRepository;
import com.example.companyProject.Service.Interfaces.CompanyServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyService implements CompanyServiceInterface {


    private final CompanyRepository companyRepository;
    private final UsersRepository usersRepository;

    public Page<CompanyProjection> getAllCompanies( Pageable pageable) {

        return companyRepository.findAllBy(pageable);
    }

    public Page<CompanyProjection> getCompanies(String name, Pageable pageable) {
        if (name == null || name.isEmpty()) {
            return getAllCompanies(pageable);
        } else {
            return searchByName(name, PageRequest.of(pageable.getPageNumber(),
                    pageable.getPageSize(),Sort.by("CompanyName").ascending().and(Sort.by("id"))));
        }
    }
    private Page<CompanyProjection> getAllUsersHelperMethod(Pageable pageable){
        return companyRepository.findAllBy(pageable);
    }

    public Page<CompanyProjection> getAllCompaniesBy(String name , CompanyStatus status , Pageable pageable){

        if(name==null && status==null){
            return companyRepository.findCompaniesWithUserStatusCounts(pageable);
        }
        else if(name==null){
            System.out.println(status.name());
            return companyRepository.findUsersByStatus(status,pageable);

        }
        else if(status==null){
            return companyRepository.findAllByCompanyNameContainingIgnoreCase(name,pageable);
        }
        else{
            System.out.println(status.name());
            return companyRepository.findAllByStatusAndCompanyNameContainingIgnoreCase(name, status,pageable);
        }

    }

    public String deleteById(int id){

        if(companyRepository.existsById(id)){

            companyRepository.deleteById(id);
            return "company " + id + " deleted";
        }else{
            throw new BadRequestException("id not exists");

        }


    }

    public String addCompany(String email,String companyName , String location){
        if(companyRepository.existsByEmail(email)){
            throw new BadRequestException("Email already exists");
        }
        else if(companyRepository.existsByCompanyName(companyName)){
            throw new BadRequestException("Name already exists");
        }
        else {

            companyRepository.addCompany(email, companyName, location);
            return "company " +companyName+" added";
        }
    }

    public String updateCompany(String email ,String companyName , String location){
        if(companyRepository.existsByCompanyName(companyName)){
            throw new BadRequestException("company name already exists");
        }
        else if(!companyRepository.existsByEmail(email)){
            throw new BadRequestException("email not found");
        }
        else {

            companyRepository.updateCompany(email,companyName, location);
            return "updated done";
        }


    }

    public CompanyProjection searchCompaniesByCompanyName(String companyName){
        return companyRepository.searchCompaniesByCompanyName(companyName);
    }

    public CompanyProjection updatecompanyByName(String companyName , Company company){
        CompanyProjection companyProjection = companyRepository.searchCompaniesByCompanyName(companyName);
        if(companyProjection!=null){
            companyRepository.updateCompany(company.getEmail(),company.getCompanyName(),company.getLocation());
            return companyRepository.searchCompaniesByCompanyName(companyName);
        }
        return null;

    }

    public Page<CompanyProjection> searchByName(String name, Pageable pageable) {
        return companyRepository.findByCompanyNameContainingIgnoreCase(name, pageable);
    }

    public List<CompanyUsersCount> countUsersByCompany(){
        return companyRepository.countUsersByCompany();
    }

    public List<CompanyListAndusers> companiesAndUsers(){
        return companyRepository.companiesAndUsers();
    }

    public void updateStatus(int id, CompanyStatus status){
        companyRepository.updateById(id,status.name());
        if(status==CompanyStatus.DISABLE){
            companyRepository.deleteById(id);
            usersRepository.markUsersDeletedByCompanyId(id);
        }
    }

    public CompanyCounts getMyCompanyUserCounts() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        MyUserDetails userDetails = (MyUserDetails) auth.getPrincipal();
        int companyId = userDetails.getCompanyId();

        return companyRepository.getCompanyUserStatusCounts(companyId);
    }




}



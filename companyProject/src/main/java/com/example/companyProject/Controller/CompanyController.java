package com.example.companyProject.Controller;

import com.example.companyProject.Controller.Exception.BadRequestException;
import com.example.companyProject.Controller.Interfaces.CompanyControllerInterface;
import com.example.companyProject.Entity.Company;
import com.example.companyProject.Enums.CompanyStatus;
import com.example.companyProject.Enums.UserStatus;
import com.example.companyProject.Projection.*;
import com.example.companyProject.Service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/companies")
public class CompanyController implements CompanyControllerInterface {

    private final CompanyService companyService;
    @GetMapping
    public Page<CompanyProjection> getAllCompanies(@RequestParam(required = false) String name , @RequestParam(required = false) CompanyStatus status,
                                             Pageable pageable){

        return companyService.getAllCompaniesBy(name, status, pageable);
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable int id){

        return companyService.deleteById(id);
    }
    @PostMapping
    public String addCompany(@RequestBody Company company){
       return companyService.addCompany(company.getEmail(),company.getCompanyName(),company.getLocation());
    }
    @PutMapping
    public String updateCompany(@RequestBody Company company){
        return companyService.updateCompany(company.getEmail(), company.getCompanyName(), company.getLocation());
    }

    @GetMapping("/{companyName}")
    public CompanyProjection searchCompaniesByCompanyName(@PathVariable String companyName){
        return companyService.searchCompaniesByCompanyName(companyName);
    }

    @GetMapping("/Users-count")
    public List<CompanyUsersCount> countUsersByCompany(){
        return companyService.countUsersByCompany();
    }

    @GetMapping("/List")
    public List<CompanyListAndusers> companiesAndUsers(){
        return companyService.companiesAndUsers();
    }

    @PutMapping("/{id}/{status}")
    public void updateStatus(@PathVariable int id,@PathVariable CompanyStatus status){
        companyService.updateStatus(id,status);
    }
    @GetMapping("/counts")
    public CompanyCounts getMyCompanyUsersStatusCount() {
        return companyService.getMyCompanyUserCounts();
    }
}

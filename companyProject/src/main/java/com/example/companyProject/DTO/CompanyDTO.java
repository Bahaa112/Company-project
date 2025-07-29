package com.example.companyProject.DTO;

public class CompanyDTO {

    private Integer id;
    private String companyName;
    private String location;

    public CompanyDTO(Integer id, String companyName, String location) {
        this.id = id;
        this.companyName = companyName;
        this.location = location;
    }
}

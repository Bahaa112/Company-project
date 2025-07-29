package com.example.companyProject.Repository;

import com.example.companyProject.Entity.Company;
import com.example.companyProject.Enums.CompanyStatus;
import com.example.companyProject.Enums.UserStatus;
import com.example.companyProject.Projection.*;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository  extends JpaRepository<Company,Integer> {

    public Page<CompanyProjection> findAllBy(Pageable pageable);
    @Modifying
    @Transactional
    @Query(value = "update company set isDeleted=true where companyName=:companyName" , nativeQuery = true)
    public void deleteCompaniesByName(String companyName);

    @Modifying
    @Transactional
    @Query(value = "insert into company(email,companyName,location) values (:email,:companyName,:location);" , nativeQuery = true)
    public void addCompany(String email , String companyName , String location);

    @Modifying
    @Transactional
    @Query(value = "update company set email=:email , companyName=:companyName , location=:location where email=:email;" , nativeQuery = true)
    public void updateCompany(String email , String companyName , String location);

    @Query(value = "select * from company where companyName = :companyName;", nativeQuery = true)
    public CompanyProjection searchCompaniesByCompanyName(String companyName);

    boolean existsByCompanyName(String companyName);
    boolean existsByEmail(String email);
    Page<CompanyProjection> findByCompanyNameContainingIgnoreCase(String name, Pageable pageable);
    boolean findCompaniesByCompanyName(String companyName);
    void deleteCompaniesByCompanyName(String companyName);
    boolean existsById(int Id);

    @Query(value="select count(u.id) as usercount , c.companyname from company c join users u on c.id = u.company.id group by companyname;" , nativeQuery = true)
    List<CompanyUsersCount> countUsersByCompany();

    @Query(value = "select c.id as companyid , c.companyname as companyname , c.email as companyemail , c.location as companylocation , u.id as userid , u.name as username , u.email as useremail from company c left join users u on c.id=u.companyid;", nativeQuery = true)
    List<CompanyListAndusers> companiesAndUsers();

    String getCompanyByIdIs(int id);
    Page<CompanyProjection> findUsersByStatus(CompanyStatus status, Pageable pageable);
    public Page<CompanyProjection> findAllByCompanyNameContainingIgnoreCase(String name, Pageable pageable);
    Page<CompanyProjection> findAllByStatusAndCompanyNameContainingIgnoreCase(String name , CompanyStatus status,Pageable pageable);

    @Modifying
    @Transactional
    @Query(value = "update company set status=:status where id=:id;" , nativeQuery = true)
    void updateById(int id, String status);

    @Query(value = "select c.id , c.companyname , c.email , c.location , c.creationdate , c.updateddate , c.status ,  COUNT(IF(u.status = 'APPROVE', 1, NULL)) AS approvedUsersCount," +
            "        COUNT(IF(u.status = 'REJECT', 1, NULL)) AS rejectedUsersCount from company c left join users u on u.companyid=c.id group by c.id ;", nativeQuery = true)
    Page<CompanyProjection> findCompaniesWithUserStatusCounts(Pageable pageable);

    @Query(value = "select c.id ,c.companyname, COUNT(IF(u.status = 'approve', 1, NULL)) AS approvedUsersCount," +
            "        COUNT(IF(u.status = 'reject', 1, NULL)) AS rejectedUsersCount from company c left join users u on u.companyid=c.id where c.id = :companyId group by c.id , c.companyName ;", nativeQuery = true)
    CompanyCounts getCompanyUserStatusCounts(int companyId);

    @Override
    boolean existsById(Integer integer);
    @Modifying
    @Transactional
    @Query(value = "update company set isDeleted=true where id=:id" , nativeQuery = true)
    void deleteById(int id);
}

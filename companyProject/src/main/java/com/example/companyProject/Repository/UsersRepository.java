package com.example.companyProject.Repository;

import com.example.companyProject.DTO.UserDTO;
import com.example.companyProject.Entity.Users;
import com.example.companyProject.Enums.UserStatus;
import com.example.companyProject.Projection.CompanyProjection;
import com.example.companyProject.Projection.CompanyUsersCount;
import com.example.companyProject.Projection.UsersProjection;
import jakarta.transaction.Transactional;
import org.apache.catalina.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {

    //@Query(value = "select * from users", nativeQuery = true)
    public Page<UsersProjection> findAllBy(Pageable pageable);

    @Modifying
    @Transactional
    @Query(value = "update users set isDeleted=true where name=:name", nativeQuery = true)
    public void deleteUserByName(String name);

    @Modifying
    @Transactional
    @Query(value = "insert into users(email,name,password,companyid) values (:email , :name , :password , :companyid);", nativeQuery = true)
    public void addUser(String email, String name, String password, int companyid);

    @Modifying
    @Transactional
    @Query(value = "update users set email=:email , name=:name , password=:password , companyid=:companyid where email=:email;", nativeQuery = true)
    public void updateUser(String email, String name, String password, int companyid);

    public Page<UsersProjection> findAllByNameContainingIgnoreCase(String name, Pageable pageable);
    boolean existsByName(String name);
    boolean existsByEmail(String email);

    Users findUsersByName(String name);


    Page<UsersProjection> findUsersByStatus(UserStatus status, Pageable pageable);
    @Modifying
    @Transactional
    @Query(value = "update users set status=:status where id=:id;" , nativeQuery = true)
    void updateById(int id, String status);

    Page<UsersProjection> findAllByNameContainingIgnoreCaseAndStatus(String name , UserStatus status,Pageable pageable);

    Users findUsersByEmail(String email);

    boolean existsById(int id);

    @Modifying
    @Transactional
    @Query(value = "update users set isDeleted=true where id=:id;",nativeQuery = true)
    void deleteById(int id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE users SET isDeleted = true WHERE companyid = :companyId", nativeQuery = true)
    void markUsersDeletedByCompanyId(@Param("companyId") int companyId);








}

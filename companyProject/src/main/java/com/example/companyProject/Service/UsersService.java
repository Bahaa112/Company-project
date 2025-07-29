package com.example.companyProject.Service;

import com.example.companyProject.Controller.Exception.BadRequestException;
import com.example.companyProject.DTO.LoginReturnDTO;
import com.example.companyProject.Entity.MyUserDetails;
import com.example.companyProject.Entity.Users;
import com.example.companyProject.Enums.UserStatus;
import com.example.companyProject.Projection.UsersProjection;
import com.example.companyProject.Repository.CompanyRepository;
import com.example.companyProject.Repository.UsersRepository;
import com.example.companyProject.Service.Interfaces.UsersServiceInterface;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor

public class UsersService  implements UsersServiceInterface {

    private final JWTService jwtService;

    private final UsersRepository usersRepository;

    private final CompanyRepository companyRepository;

    private final AuthenticationManager authenticationManager;

    private Page<UsersProjection> getAllUsersHelperMethod(Pageable pageable){
        return usersRepository.findAllBy(pageable);
    }

    public String deleteById(int id){
        if(usersRepository.existsById(id)){

            usersRepository.deleteById(id);
            return "user " + id + " deleted";
        }else{
            throw new BadRequestException("Name not exists");

        }

    }

    public String addUser(String email, String name, String password, int companyId) {

        if (usersRepository.existsByEmail(email)) {
            throw new BadRequestException("Email already exists");
        }
        if (!companyRepository.existsById(companyId)) {
            throw new BadRequestException("Company ID does not exist");
        }

        usersRepository.addUser(email, name, password, companyId);
        return "User " + name + " added successfully";
    }

    public void updateUser(String email,String name , String password ){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        MyUserDetails userDetails = (MyUserDetails) auth.getPrincipal();
        int companyId = userDetails.getCompanyId();


        if (!companyRepository.existsById(companyId)) {
            throw new BadRequestException("Company ID does not exist");
        }
        if(!usersRepository.existsByEmail(email)){
            throw new BadRequestException("email does not exists");
        }
        usersRepository.updateUser(email,name,password,companyId);


    }

    public Page<UsersProjection> searchByName(String name, Pageable pageable) {
        return usersRepository.findAllByNameContainingIgnoreCase(name,pageable);
    }

    public Object verify(Users users) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(users.getName(), users.getPassword()));
            if (authentication.isAuthenticated()) {
                Users users1 = usersRepository.findUsersByName(users.getName());
                String token = jwtService.generateToken(users1);

                return new LoginReturnDTO(token,users1.getName(),users1.getEmail(),users1.getId(),users1.getCompanyId(),users1.getCreationDate(),users1.getUpdatedDate());
            } else {
                return "fail";
            }
        }catch (AuthenticationException e) {
            return "Invalid username or password";
        }
    }


    public void updateStatus(int id, UserStatus status){
            usersRepository.updateById(id,status.name());
    }

    public Page<UsersProjection> getAllUsersBy(String name , UserStatus status , Pageable pageable){

        if(name==null && status==null){
            return getAllUsersHelperMethod(pageable);
        }
        else if(name==null){
            System.out.println(status.name());
            return usersRepository.findUsersByStatus(status,pageable);

        }
        else if(status==null){
            return usersRepository.findAllByNameContainingIgnoreCase(name,pageable);
        }
        else{
            System.out.println(status.name());
            return usersRepository.findAllByNameContainingIgnoreCaseAndStatus(name, status,pageable);
        }

    }

}

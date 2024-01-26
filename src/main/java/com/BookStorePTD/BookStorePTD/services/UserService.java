package com.BookStorePTD.BookStorePTD.services;

import com.BookStorePTD.BookStorePTD.components.JwtTokenUtil;
import com.BookStorePTD.BookStorePTD.dtos.UserDto;
import com.BookStorePTD.BookStorePTD.exception.DataNotFound;
import com.BookStorePTD.BookStorePTD.exception.PermissionDenyException;
import com.BookStorePTD.BookStorePTD.models.Role;
import com.BookStorePTD.BookStorePTD.models.User;
import com.BookStorePTD.BookStorePTD.repositories.RoleRepository;
import com.BookStorePTD.BookStorePTD.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService implements  IUserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;
    @Override
    public void createUser(UserDto userDto) throws Exception{
        if(userRepository.existsByUserName(userDto.getUserName())){
            throw new DataIntegrityViolationException("Username already exist !!! ");
        }
        if(!userDto.getPassword().equals(userDto.getRetypePassword())){
            throw new DataIntegrityViolationException("PassWord does not match !!! ");
        }
        User newUser= User.builder().userName(userDto.getUserName())
                .email(userDto.getEmail())
                .fullName(userDto.getFullName())
                .phoneNumber(userDto.getPhoneNumber())
                .address(userDto.getAddress())
                .dateOfBirth(userDto.getDateOfBirth())
                .googleAccountId(userDto.getGoogleAccountId())
                .facebookAccountId(userDto.getFaceBookAccountId())
                .build();
        Role role= roleRepository.findById(userDto.getRoleId()).orElseThrow(() -> new DataNotFound("Not found Role with role_id= "+userDto.getRoleId()));
        if(role.getId() != 1){
            throw new PermissionDenyException("User only create Account User !!!");
        }
        newUser.setRole(role);
        if(userDto.getGoogleAccountId() == 0 && userDto.getFaceBookAccountId() ==0){
            String passWordEncode= bCryptPasswordEncoder.encode(userDto.getPassword());
            newUser.setPassword(passWordEncode);
        }
        userRepository.save(newUser);
    }

    @Override
    public ResponseEntity<?> login(String userName, String passWord) throws Exception{
        Optional<User> optionalUser= userRepository.findByUserName(userName);
        if(optionalUser.isEmpty()){
           throw new DataNotFound("Username of password invalid !!!");
        }
        User userExist=optionalUser.get();
        if(userExist.getGoogleAccountId()==0 && userExist.getFacebookAccountId() ==0){
            if(!bCryptPasswordEncoder.matches(passWord,userExist.getPassword())){
                throw new BadCredentialsException("Wrong Username or password !");
            }
        }
        UsernamePasswordAuthenticationToken authenticationToken= new UsernamePasswordAuthenticationToken(userName,passWord,userExist.getAuthorities());
        authenticationManager.authenticate(authenticationToken);
        Map<String,String> token = new HashMap<>();
        token.put("token",jwtTokenUtil.generateToken(userExist));
        return ResponseEntity.ok().body(token);
    }
}

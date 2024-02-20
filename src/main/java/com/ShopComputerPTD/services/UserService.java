package com.ShopComputerPTD.services;

import com.ShopComputerPTD.components.JwtTokenUtil;
import com.ShopComputerPTD.dtos.UserDto;
import com.ShopComputerPTD.exception.DataNotFound;
import com.ShopComputerPTD.exception.PermissionDenyException;
import com.ShopComputerPTD.models.Role;
import com.ShopComputerPTD.models.User;
import com.ShopComputerPTD.repositories.RoleRepository;
import com.ShopComputerPTD.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
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

    @Override
    public String getRoleByToken(String token) {
        token= token.substring(7);
        return jwtTokenUtil.extractRoleName(token);
    }

    @Override
    public User getUserByToken(String token) {
        if(jwtTokenUtil.isTokenExpired(token)){
            throw new RuntimeException("token is Expired");
        }
        String userName= jwtTokenUtil.extractUsername(token);
        Optional<User> user= userRepository.findByUserName(userName);
        if(user.isPresent()){
            return user.get();
        }
        throw new DataNotFound("Not Found User with UserName = "+userName);
    }

    @Override
    public List<User> getAllAccount() {
        return userRepository.findAll();
    }
}

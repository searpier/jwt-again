package com.example.jwt5.service;

import com.example.jwt5.dto.UserDTO;
import com.example.jwt5.entity.User;
import com.example.jwt5.jwt.JwtProvider;
import com.example.jwt5.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    public String signup(UserDTO userDTO){
        userRepository.save(userDTO.toEntity());
        return "success";
    }

    public String signin(UserDTO userDTO){
        Optional<User> loggedIn=userRepository.findByEmailAndPassword(userDTO.getEmail(),userDTO.getPassword());
        try{
            User user=loggedIn.get();
            return jwtProvider.token(user);
        }catch(NoSuchElementException e){
            return "failed";
        }
    }

}

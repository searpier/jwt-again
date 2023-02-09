package com.example.jwt5.jwt;

import com.example.jwt5.dto.UserDTO;
import com.example.jwt5.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtProvider {
    //토큰을 발급하거나, 토큰을 유저 객체로 바꾸는 클래스

    public String token(User user){
        Date date=new Date();

        return Jwts.builder()
                .setHeaderParam("typ","JWT")
                .setIssuer("james")
                .setIssuedAt(date)
                .setExpiration(new Date(date.getTime()+3600000))
                .claim("email",user.getEmail())
                .signWith(SignatureAlgorithm.HS256,"12345")
                .compact();

    }

    public Claims tokenToUser(String token){ // "Bearer sdmleeweawlek.ekwekwekewrkj.enwerkjwerknrwekn"

        if(token!=null) {
            token = token.substring("Bearer".length());

            return Jwts.parser()
                    .setSigningKey("12345")
                    .parseClaimsJws(token)
                    .getBody();

        }else{
            return null;
        }

    }

}

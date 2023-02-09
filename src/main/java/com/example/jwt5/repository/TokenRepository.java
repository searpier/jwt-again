package com.example.jwt5.repository;

import com.example.jwt5.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token,String> {

    boolean existsByToken(String token);

}

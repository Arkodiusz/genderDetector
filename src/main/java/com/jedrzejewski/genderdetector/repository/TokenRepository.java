package com.jedrzejewski.genderdetector.repository;

import com.jedrzejewski.genderdetector.data.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {

    @Query(nativeQuery = true)
    List<Token> retrieveByGender(
            @Param("gender") char gender
    );

    @Query(nativeQuery = true)
    List<Token> retrieveByGenderWithPagination(
            @Param("gender") char gender,
            @Param("limit") int limit,
            @Param("offset") int offset
    );
}

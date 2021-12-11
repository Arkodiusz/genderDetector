package com.jedrzejewski.genderdetector.repository;

import com.jedrzejewski.genderdetector.data.Token;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {

    @NonNull List<Token> findAll();

    @NonNull Token save(@NonNull Token token);

    @Override
    void deleteById(@NonNull Long id);

    @Query(nativeQuery = true)
    List<Token> retrieveByGender(@Param("gender") char gender);
}

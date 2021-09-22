package com.jedrzejewski.genderdetector;

import lombok.NonNull;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface TokenRepository extends CrudRepository<Token, Long> {

    @NonNull List<Token> findAll();

    @NonNull Token save(@NonNull Token token);

    @Override
    void deleteById(@NonNull Long id);

    @Query(nativeQuery = true)
    List<Token> retrieveByGender(@Param("gender") char gender);

    @Query(nativeQuery = true)
    List<Token> retrieveByNation(@Param("nation") String nation);
}

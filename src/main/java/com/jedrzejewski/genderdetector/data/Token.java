package com.jedrzejewski.genderdetector.data;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NamedNativeQuery(
        name = "Token.retrieveByGender",
        query = "SELECT * FROM TOKENS WHERE GENDER = :gender ",
        resultClass = Token.class
)
@NamedNativeQuery(
        name = "Token.retrieveByGenderWithPagination",
        query = "SELECT * FROM TOKENS WHERE GENDER = :gender " +
                "ORDER BY NAME " +
                "LIMIT :limit OFFSET :offset ",
        resultClass = Token.class
)
@Entity(name = "TOKENS")
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @lombok.NonNull
    private String name;

    private char gender;
}

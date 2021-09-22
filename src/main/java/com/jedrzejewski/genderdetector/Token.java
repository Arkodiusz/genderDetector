package com.jedrzejewski.genderdetector;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NamedNativeQuery(
        name = "Token.retrieveByGender",
        query = "SELECT * FROM TOKENS WHERE GENDER = :gender",
        resultClass = Token.class
)
@NamedNativeQuery(
        name = "Token.retrieveByNation",
        query = "SELECT * FROM TOKENS WHERE UPPER(NATION) = UPPER(:nation)",
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
    @NonNull
    private String name;

    //@NotNull
    //@NonNull
    private char gender;

    //@NotNull
    //@NonNull
    private String nation;
}

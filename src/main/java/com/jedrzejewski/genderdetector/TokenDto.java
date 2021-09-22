package com.jedrzejewski.genderdetector;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TokenDto {
    private Long id;
    private String name;
    private char gender;
}

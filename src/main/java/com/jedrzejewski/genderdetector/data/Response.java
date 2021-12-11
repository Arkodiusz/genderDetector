package com.jedrzejewski.genderdetector.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Response {
    private String designation;
    private int male;
    private int female;
    private int inconclusive;
    private double percentage;
}

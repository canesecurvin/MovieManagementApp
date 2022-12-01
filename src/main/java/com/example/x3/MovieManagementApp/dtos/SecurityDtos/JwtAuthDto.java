package com.example.x3.MovieManagementApp.dtos.SecurityDtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class JwtAuthDto {
    private String acessToken;
    private String tokenType = "Bearer";

    public JwtAuthDto(String acessToken) {
        this.acessToken = acessToken;
    }
}

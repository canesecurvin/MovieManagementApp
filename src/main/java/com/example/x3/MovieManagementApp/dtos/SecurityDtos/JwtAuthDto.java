package com.example.x3.MovieManagementApp.dtos.SecurityDtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class JwtAuthDto {
    private String accessToken;
    private String tokenType = "Bearer";

    public JwtAuthDto(String accessToken) {
        this.accessToken = accessToken;
    }
}

package ru.sfedu.diplomabackend.security.dto;

import lombok.Data;

@Data
public class AuthResponseDto {
    private String email;
    private String token;
}

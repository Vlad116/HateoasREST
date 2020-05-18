package ru.itis.hateoasrestservice.dto;

import lombok.Data;

@Data
public class LoginDto {
  private String login;
  private String password;
}

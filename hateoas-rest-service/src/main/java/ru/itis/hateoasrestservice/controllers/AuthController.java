package ru.itis.hateoasrestservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.hateoasrestservice.dto.LoginDto;
import ru.itis.hateoasrestservice.dto.TokenDto;
import ru.itis.hateoasrestservice.dto.UserDto;
import ru.itis.hateoasrestservice.services.UserService;


@RestController
public class AuthController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody LoginDto loginDto) {
        return ResponseEntity.ok(userService.login(loginDto));
    }

    @PostMapping("/registration")
    public ResponseEntity<TokenDto> register(@RequestBody UserDto userDto) {
        try {
            userService.register(userDto);
            return ResponseEntity.ok().build();
        }catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }
}

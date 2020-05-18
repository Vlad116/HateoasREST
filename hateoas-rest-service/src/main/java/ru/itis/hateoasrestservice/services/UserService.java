package ru.itis.hateoasrestservice.services;


import ru.itis.hateoasrestservice.dto.LoginDto;
import ru.itis.hateoasrestservice.dto.TokenDto;
import ru.itis.hateoasrestservice.dto.UserDto;

public interface UserService {
    TokenDto login(LoginDto loginDto);
    void register(UserDto user);
//    void update(ProfileForm profileForm, Authentication authentication);
//    ProfileDto getUser(Authentication authentication, Long id);
//    ProfileDto getUser(Authentication authentication);

}

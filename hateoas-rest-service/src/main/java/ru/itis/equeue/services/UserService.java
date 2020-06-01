package ru.itis.equeue.services;


import ru.itis.equeue.dto.LoginDto;
import ru.itis.equeue.dto.TokenDto;
import ru.itis.equeue.dto.UserDto;

public interface UserService {
    TokenDto login(LoginDto loginDto);
    void register(UserDto user);
//    void update(ProfileForm profileForm, Authentication authentication);
//    ProfileDto getUser(Authentication authentication, Long id);
//    ProfileDto getUser(Authentication authentication);

}

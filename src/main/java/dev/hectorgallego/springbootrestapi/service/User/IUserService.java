package dev.hectorgallego.springbootrestapi.service.User;

import java.util.List;

import dev.hectorgallego.springbootrestapi.model.user.User;
import dev.hectorgallego.springbootrestapi.model.user.UserDto;

public interface IUserService {

    List<UserDto> getAllUsers();
    UserDto getUserById(Long id);
    void deleteUserById(Long id);
    User createUser(User user);
    User updateUser(User user, Long id);
    UserDto getUserByEmail(String email);
    
}

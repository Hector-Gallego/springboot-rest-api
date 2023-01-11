package dev.hectorgallego.springbootrestapi.service.User;

import java.util.List;


import dev.hectorgallego.springbootrestapi.model.User;

public interface IUserService {

    List<User> getAllUsers();
    User getUserById(Long id);
    void deleteUserById(Long id);
    User createUser(User user);
    User updateUser(User user, Long id);
    
}

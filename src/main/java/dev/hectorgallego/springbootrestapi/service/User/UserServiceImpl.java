package dev.hectorgallego.springbootrestapi.service.User;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.hectorgallego.springbootrestapi.model.User;
import dev.hectorgallego.springbootrestapi.repository.UserRepository;

@Service
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    @Transactional
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public User updateUser(User user, Long id) {

        User userPersist = userRepository.findById(id).get();

        userPersist.setFirstName(user.getFirstName());
        userPersist.setLastName(user.getLastName());
        userPersist.setPassword(user.getPassword());
        userPersist.setRoles(user.getRoles());

        return userRepository.save(userPersist);
        
    }
    
}

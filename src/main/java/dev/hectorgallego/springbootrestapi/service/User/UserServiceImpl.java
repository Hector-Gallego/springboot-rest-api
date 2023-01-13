package dev.hectorgallego.springbootrestapi.service.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.hectorgallego.springbootrestapi.model.Role;
import dev.hectorgallego.springbootrestapi.model.User;
import dev.hectorgallego.springbootrestapi.repository.RoleRepository;
import dev.hectorgallego.springbootrestapi.repository.UserRepository;

@Service
public class UserServiceImpl implements IUserService {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(RoleRepository roleRepository, UserRepository userRepository,
            PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
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

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        List<Role> roles = user.getRoles();
        List<Role> rolesPersist = new ArrayList<>();
        for (Role role : roles){
            rolesPersist.add(roleRepository.findById(role.getId()).get());
        }
        
        user.setRoles(rolesPersist);
        String token =  UUID.randomUUID().toString();
        user.setTokenVerificaton(token);
        user.setEnabled(false); 
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

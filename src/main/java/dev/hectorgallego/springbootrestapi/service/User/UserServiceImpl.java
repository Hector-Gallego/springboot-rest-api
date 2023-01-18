package dev.hectorgallego.springbootrestapi.service.User;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.hectorgallego.springbootrestapi.mapperdto.UserMapper;
import dev.hectorgallego.springbootrestapi.model.Role;
import dev.hectorgallego.springbootrestapi.model.user.User;
import dev.hectorgallego.springbootrestapi.model.user.UserDto;
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
    public List<UserDto> getAllUsers() {
        
        return userRepository
            .findAll()
            .stream()
            .map((user)-> UserMapper.mapperUser(user))
            .collect(Collectors.toList());
        
    }


    @Override
    @Transactional(readOnly = true)
    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(()-> new NoSuchElementException("no se encointro al usuario con id: "+ id));
        return UserMapper.mapperUser(user);
    }


    @Override
    @Transactional
    public void deleteUserById(Long id) {

        getUserById(id);
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

        User userPersist = userRepository.findById(id).orElseThrow( ()-> new NoSuchElementException("no se encointro al usuario con id: "+ id));

        userPersist.setFirstName(user.getFirstName());
        userPersist.setLastName(user.getLastName());
        userPersist.setPassword(user.getPassword());
        userPersist.setRoles(user.getRoles());

        return userRepository.save(userPersist);
        
    }

    @Override
    public UserDto getUserByEmail(String email) {

        User user = userRepository.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException("no se encontro al usuario con email: "+ email));
        return UserMapper.mapperUser(user);
            

    }
    
}

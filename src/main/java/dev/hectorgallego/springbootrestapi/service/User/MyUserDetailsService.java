package dev.hectorgallego.springbootrestapi.service.User;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import dev.hectorgallego.springbootrestapi.model.MyUserDetails;
import dev.hectorgallego.springbootrestapi.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService{

    private final UserRepository userRepository;
    public MyUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException  {
        

        System.out.println("************** buscando al usuario: "+ username + " ***********************");
        return userRepository
            .findByEmail(username)
            .map(MyUserDetails::new)
            .orElseThrow(() -> new UsernameNotFoundException("credenciales incorrectas para el usuario: "+ username));       
    }
    
}

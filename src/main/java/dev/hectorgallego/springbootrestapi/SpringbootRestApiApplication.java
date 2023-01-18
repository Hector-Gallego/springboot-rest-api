package dev.hectorgallego.springbootrestapi;


import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import dev.hectorgallego.springbootrestapi.config.security.RsaKeyProperties;
import dev.hectorgallego.springbootrestapi.model.Role;
import dev.hectorgallego.springbootrestapi.model.user.User;
import dev.hectorgallego.springbootrestapi.repository.RoleRepository;
import dev.hectorgallego.springbootrestapi.repository.UserRepository;


@SpringBootApplication
@EnableConfigurationProperties(RsaKeyProperties.class)
//@EnableAsync
public class SpringbootRestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootRestApiApplication.class, args);
	}

}

@Component
class DemoCommandLineRunner implements CommandLineRunner {


	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final PasswordEncoder encoder;
	

	public DemoCommandLineRunner(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder encoder) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.encoder = encoder;
	}


	@Override
	public void run(String... args) throws Exception {
		
		Role role = new Role();

		role.setRole("ADMIN");
		roleRepository.save(role);

		User user = new User();

		user.setFirstName("admin_test");
		user.setLastName("admin_test");
		user.setEmail("admin@gmail.com");
		user.setPassword(encoder.encode("admin"));
		user.setEnabled(true);
		user.setTokenVerificaton(null);
		user.setRoles(Arrays.asList(role));

		userRepository.save(user);

	}

}

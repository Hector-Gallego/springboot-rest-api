package dev.hectorgallego.springbootrestapi.mapperdto;



import dev.hectorgallego.springbootrestapi.model.user.User;
import dev.hectorgallego.springbootrestapi.model.user.UserDto;

public class UserMapper {

    public static UserDto mapperUser(User user) {

        return  new UserDto(
                        user.getId(),
                        user.getFirstName(),
                        user.getLastName(),
                        user.getEmail(),
                        user.getRoles());

    }

}

package ru.sfedu.diplomabackend.security.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.sfedu.diplomabackend.model.User;

import java.util.Date;

@Data
@NoArgsConstructor
public class UserPostRequestDto {

    private String email;
    private String password;
    private String firstName;
    private String lastName;

    public UserPostRequestDto(User user) {
        email = user.getEmail();
        password = user.getPassword();
        firstName = user.getFirstName();
        lastName = user.getLastName();
    }

    public User toUser() {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
       // user.setCreated(new Date());
        return user;
    }

}

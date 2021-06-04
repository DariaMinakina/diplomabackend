package ru.sfedu.diplomabackend.controller;

import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.sfedu.diplomabackend.Constants;
import ru.sfedu.diplomabackend.dao.user.UserDao;
import ru.sfedu.diplomabackend.model.User;
import ru.sfedu.diplomabackend.security.JwtTokenProvider;
import ru.sfedu.diplomabackend.security.dto.UserPostRequestDto;
import ru.sfedu.diplomabackend.service.user.UserService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/user")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class UserController {

    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List getAllUsers() {
        return userService.getUsers();
    }

    @GetMapping
    public ResponseEntity<User> getUser(@RequestHeader("Authorization") String token) {
        var optUser = userService.findByEmail(jwtTokenProvider.getUsername(token));
        return optUser.map(user -> ResponseEntity.ok(user))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody UserPostRequestDto userPostRequestDto) {
        var user = userPostRequestDto.toUser();
        var serverUser = userService.findByEmail(user.getEmail()).orElseThrow(()
                -> new UsernameNotFoundException(Constants.USER_NOT_FOUND_CONST));
        user.setId(serverUser.getId());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userService.updateUser(user)
                ? ResponseEntity.ok(user)
                : new ResponseEntity<>("Invalid user", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping
    public boolean deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }

}

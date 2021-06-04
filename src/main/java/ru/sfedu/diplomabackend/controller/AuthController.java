package ru.sfedu.diplomabackend.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;
import ru.sfedu.diplomabackend.dao.user.UserDao;
import ru.sfedu.diplomabackend.model.User;
import ru.sfedu.diplomabackend.security.JwtTokenProvider;
import ru.sfedu.diplomabackend.security.dto.AuthRequestDto;
import ru.sfedu.diplomabackend.security.dto.AuthResponseDto;
import ru.sfedu.diplomabackend.security.dto.UserPostRequestDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserDao userDao;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody AuthRequestDto requestDto) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(requestDto.getEmail(),
                    requestDto.getPassword()));
            User user = userDao.findByEmail(requestDto.getEmail()).get();
            String token = jwtTokenProvider.createToken(requestDto.getEmail());
            var response = new AuthResponseDto();
            response.setEmail(requestDto.getEmail());
            response.setToken(token);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (AuthenticationException e) {
            return new ResponseEntity<>("Invalid email/password combination", HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        var securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(request, response, null);
    }

    @PostMapping("/register")
    public ResponseEntity<?> createUser(@RequestBody UserPostRequestDto userPostRequestDto) {
        var user = userPostRequestDto.toUser();
        Date date = new Date();
        //user.setCreated(date);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userDao.addUser(user)
                ? ResponseEntity.ok(user)
                : new ResponseEntity<>("Invalid user", HttpStatus.BAD_REQUEST);
    }
}

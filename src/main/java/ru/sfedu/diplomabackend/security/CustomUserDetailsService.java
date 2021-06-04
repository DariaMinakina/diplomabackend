package ru.sfedu.diplomabackend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.sfedu.diplomabackend.Constants;
import ru.sfedu.diplomabackend.dao.user.UserDao;
import ru.sfedu.diplomabackend.model.User;

@Service("userDetailsServiceImpl")
public class CustomUserDetailsService implements UserDetailsService {

    private final UserDao userRepo;

    @Autowired
    public CustomUserDetailsService(UserDao userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(username).get();
        if (user == null) {
            throw new UsernameNotFoundException(Constants.USER_NOT_FOUND_CONST);
        }
        return new CustomUserDetails(user);
    }

}
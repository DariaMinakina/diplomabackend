package ru.sfedu.diplomabackend.service.user;

import org.springframework.stereotype.Service;
import ru.sfedu.diplomabackend.model.Goal;
import ru.sfedu.diplomabackend.model.User;

import java.util.List;
import java.util.Optional;


public interface MetaUserService {

    Optional<User> getById(Long id);

    boolean addUser (User user);

    boolean updateUser (User user);

    boolean deleteUser (Long id);

    List getUsers();

    Optional<User> findByEmail(String email);

}

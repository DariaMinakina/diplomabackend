package ru.sfedu.diplomabackend.service.user;

import ru.sfedu.diplomabackend.model.Goal;
import ru.sfedu.diplomabackend.model.User;

import java.util.List;
import java.util.Optional;

public interface MetaUserService {

    User getById(Long id);

    Optional<Long> addUser (User user);

    boolean updateUser (User user);

    boolean deleteUser (Long id);

    List getUsers();

}

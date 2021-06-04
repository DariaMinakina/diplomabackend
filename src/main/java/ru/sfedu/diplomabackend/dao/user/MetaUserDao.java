package ru.sfedu.diplomabackend.dao.user;

import ru.sfedu.diplomabackend.model.User;

import java.util.List;
import java.util.Optional;

public interface MetaUserDao {

    Optional<User> getById(Long id);

    boolean addUser (User user);

    boolean updateUser (User user);

    boolean deleteUser (Long id);

    List getUsers();

    Optional<User> findByEmail(String email);

}

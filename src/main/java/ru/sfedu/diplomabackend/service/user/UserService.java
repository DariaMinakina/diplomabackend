package ru.sfedu.diplomabackend.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sfedu.diplomabackend.dao.goal.GoalDao;
import ru.sfedu.diplomabackend.dao.user.UserDao;
import ru.sfedu.diplomabackend.model.User;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService implements MetaUserService{

    @Autowired
    private UserDao userDao;

    @Override
    public User getById(Long id) {
        return userDao.getById(id);
    }

    @Override
    public Optional<Long> addUser(User user) {
        return userDao.addUser(user);
    }

    @Override
    public boolean updateUser(User user) {
        return userDao.updateUser(user);
    }

    @Override
    public boolean deleteUser(Long id) {
        return userDao.deleteUser(id);
    }

    @Override
    public List getUsers() {
        return userDao.getUsers();
    }
}

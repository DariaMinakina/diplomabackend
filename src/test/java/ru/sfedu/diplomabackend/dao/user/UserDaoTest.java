package ru.sfedu.diplomabackend.dao.user;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import ru.sfedu.diplomabackend.model.User;

import java.util.Date;
import java.util.List;
import java.util.Optional;

class UserDaoTest {


    private static Logger log = LogManager.getLogger(UserDaoTest.class);

    public UserDaoTest(){}


    @Test
    @Order(0)
    void addUserSuccess() {
        log.info("addUserSuccess");
        Date date = new Date();
        User user = new User("email@gmail.com", "qwerty123", "daniel", "matrinson",  date);
        UserDao instance = new UserDao();
        Optional<Long> result = instance.addUser(user);
        log.info(result);
        User entity = instance.getById(result.get());
        log.debug(result.get());
        Assertions.assertEquals(user, entity);
    }

    @Test
    @Order(1)
    void addUserFail() {
        log.info("addUserFail");
        Date date = new Date();
        User user = new User("email@gmail.com", null, "daniel", "matrinson",  date);
        UserDao instance = new UserDao();
        Optional<Long> result = instance.addUser(user);
        log.info(result);
        Assertions.assertNull(user);
    }

    @Test
    @Order(3)
    public void getByIdUserSuccess() {
        log.info("getByIdUserSuccess");
        Date date = new Date();
        User user = new User("email@gmail.com", "qwerty123", "daniel", "matrinson",  date);
        UserDao instance = new UserDao();
        Optional<Long> result = instance.addUser(user);
        log.info(result);
        Assertions.assertNotNull(result);
    }

    @Test
    @Order(4)
    public void getByIdFail() {
        log.info("getByIdFail");
        Date date = new Date();
        User user = new User("email@gmail.com", "qwerty123", "daniel", "matrinson",  date);
        UserDao instance = new UserDao();
        Optional<Long> result = instance.addUser(user);
        log.info(result);
        User entity = instance.getById(100L);
        Assertions.assertNull(entity);
    }

    @Test
    @Order(5)
    public void updateUserSuccess(){
        log.info("updateUserSuccess");
        Date date = new Date();
        User user = new User("email@gmail.com", "qwerty123", "daniel", "matrinson",  date);
        UserDao instance = new UserDao();
        Optional<Long> result = instance.addUser(user);
        user.setLastName("changed_name");
        Assertions.assertTrue(instance.updateUser(user));
    }

    @Test
    @Order(6)
    public void updateUserFail(){
        log.info("updateUserFail");
        Date date = new Date();
        User user = new User("email@gmail.com", "qwerty123", "daniel", "matrinson",  date);
        UserDao instance = new UserDao();
        user.setLastName(null);
        Assertions.assertFalse(instance.updateUser(user));
    }

    @Test
    @Order(7)
    public void deleteEntitySuccess(){
        log.info("deleteEntitySuccess");
        Date date = new Date();
         User user = new User("email@gmail.com", "qwerty123", "daniel", "matrinson",  date);
        UserDao instance = new UserDao();
        Optional<Long> result = instance.addUser(user);
        Assertions.assertTrue(instance.deleteUser(result.get()));
    }

    @Test
    @Order(8)
    public void deleteEntityFail(){
        log.info("deleteEntityFail");
        Date date = new Date();
        User user = new User("email@gmail.com", "qwerty123", "daniel", "matrinson",  date);
        UserDao instance = new UserDao();
        Optional<Long> result = instance.addUser(user);
        User entity = instance.getById(100L);
        Assertions.assertFalse(instance.deleteUser(entity.getId()));
    }

    @Test
    @Order(9)
    public void getAllSuccess(){
        log.info("getAllSuccess");
        UserDao instance = new UserDao();
        List all = instance.getUsers();
        log.info(all);
        Assertions.assertNotNull(all);
    }
}
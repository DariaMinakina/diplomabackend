package ru.sfedu.diplomabackend.dao.user;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import ru.sfedu.diplomabackend.dao.goal.GoalDao;
import ru.sfedu.diplomabackend.model.User;

import java.util.Date;
import java.util.List;
import java.util.Optional;



@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserDaoTest {


    private static Logger log = LogManager.getLogger(UserDaoTest.class);

    public UserDaoTest(){}


    @Test
    @Order(0)
    void addUserSuccess() {
        log.info("addUserSuccess");
        User user = new User("email@gmail.com", "qwerty123", "daniel", "matrinson");
        UserDao instance = new UserDao();
        Assertions.assertTrue( instance.addUser(user));
    }

    @Test
    @Order(1)
    void addUserFail() {
        log.info("addUserFail");
        User user = new User("email@gmail.com", null, "daniel", "matrinson");
        UserDao instance = new UserDao();
        Assertions.assertFalse( instance.addUser(user));
    }

    @Test
    @Order(2)
    public void getUserByIdSuccess() {
        log.info("getUserByIdSuccess");
        User user = new User("email@gmail.com", "qwerty123", "daniel", "matrinson");
        UserDao instance = new UserDao();
        instance.addUser(user);
        Assertions.assertNotNull(instance.getById(user.getId()));
    }

    @Test
    @Order(3)
    public void getUserByIdFail() {
        log.info("getUserByIdFail");
        User user = new User("email@gmail.com", "qwerty123", "daniel", "matrinson");
        UserDao instance = new UserDao();
        instance.addUser(user);
        Assertions.assertEquals(instance.getById(10000L), Optional.empty());

    }

    @Test
    @Order(4)
    public void updateUserSuccess(){
        log.info("updateUserSuccess");
        User user = new User("email@gmail.com", "qwerty123", "daniel", "matrinson");
        UserDao instance = new UserDao();
        instance.addUser(user);
        user.setLastName("changed_name");
        Assertions.assertTrue(instance.updateUser(user));
    }

    @Test
    @Order(6)
    public void updateUserFail(){
        log.info("updateUserFail");
        User user = new User("email@gmail.com", "qwerty123", "daniel", "matrinson");
        UserDao instance = new UserDao();
        user.setLastName(null);
        Assertions.assertFalse(instance.updateUser(user));
    }

    @Test
    @Order(7)
    public void deleteUserSuccess(){
        log.info("deleteUserSuccess");
         User user = new User("email@gmail.com", "qwerty123", "daniel", "matrinson");
        UserDao instance = new UserDao();
        instance.addUser(user);
        Assertions.assertTrue(instance.deleteUser(user.getId()));
    }

    @Test
    @Order(8)
    public void deleteUserFail(){
        log.info("deleteUserFail");
        User user = new User("email@gmail.com", "qwerty123", "daniel", "matrinson");
        UserDao instance = new UserDao();
        instance.addUser(user);
        Assertions.assertFalse(instance.deleteUser(10000L));
    }
}
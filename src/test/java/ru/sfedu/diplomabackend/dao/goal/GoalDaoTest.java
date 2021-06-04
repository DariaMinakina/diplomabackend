package ru.sfedu.diplomabackend.dao.goal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import ru.sfedu.diplomabackend.dao.user.UserDao;
import ru.sfedu.diplomabackend.model.Goal;
import ru.sfedu.diplomabackend.model.Priority;

import java.util.*;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class GoalDaoTest {

    private static Logger log = LogManager.getLogger(GoalDaoTest.class);

    public GoalDaoTest(){}


    @Test
    @Order(0)
    void addGoalSuccess() {
        log.info("addGoalSuccess");
        Date date = new Date();
        Goal goal = new Goal(date, "goal_des", Priority.PRIORITY_3);
        goal.setUserss(new UserDao().getById(1L).get());
        GoalDao instance = new GoalDao();
        Assertions.assertTrue(instance.addGoal(goal));
    }

    @Test
    @Order(1)
    void addGoalFail() {
        log.info("addGoalFail");
        Date date = new Date();
        Goal goal = new Goal(date, null, Priority.PRIORITY_3);
        goal.setUserss(new UserDao().getById(1L).get());
        GoalDao instance = new GoalDao();
        Assertions.assertFalse(instance.addGoal(goal));
    }

    @Test
    @Order(3)
    public void getByIdGoalSuccess() {
        log.info("getByIdGoalSuccess");
        Date date = new Date();
        Goal goal = new Goal(date, "goal_des", Priority.PRIORITY_3);
        goal.setUserss(new UserDao().getById(1L).get());
        GoalDao instance = new GoalDao();
        instance.addGoal(goal);
        Assertions.assertNotNull(instance.getGoalById(goal.getId()));
    }

    @Test
    @Order(4)
    public void getByIdFail() {
        log.info("getByIdGoalFail");
        Date date = new Date();
        Goal goal = new Goal(date, "goal_des", Priority.PRIORITY_3);
        goal.setUserss(new UserDao().getById(1L).get());
        GoalDao instance = new GoalDao();
        instance.addGoal(goal);
        Assertions.assertEquals(instance.getGoalById(10000L), Optional.empty());
    }

    @Test
    @Order(5)
    public void updateGoalSuccess(){
        log.info("updateGoalSuccess");
        Date date = new Date();
        Goal goal = new Goal(date, "goal_des", Priority.PRIORITY_3);
        goal.setUserss(new UserDao().getById(1L).get());
        GoalDao instance = new GoalDao();
        instance.addGoal(goal);
        goal.setDescription("changed");
        Assertions.assertTrue(instance.updateGoal(goal));
    }

    @Test
    @Order(6)
    public void updateGoalFail(){
        log.info("updateGoalFail");
        Date date = new Date();
        Goal goal = new Goal(date, "goal_des", Priority.PRIORITY_3);
        goal.setUserss(new UserDao().getById(1L).get());
        GoalDao instance = new GoalDao();
        goal.setDescription(null);
        Assertions.assertFalse(instance.updateGoal(goal));
    }

    @Test
    @Order(7)
    public void deleteGoalSuccess(){
        log.info("deleteGoalSuccess");
        Date date = new Date();
        Goal goal = new Goal(date, "goal_des", Priority.PRIORITY_3);
        goal.setUserss(new UserDao().getById(1L).get());
        GoalDao instance = new GoalDao();
        instance.addGoal(goal);
        Assertions.assertTrue(instance.deleteGoal(goal.getId()));
    }

    @Test
    @Order(8)
    public void deleteGoalFail(){
        log.info("deleteGoalFail");
        Date date = new Date();
        Goal goal = new Goal(date, "goal_des", Priority.PRIORITY_3);
        goal.setUserss(new UserDao().getById(1L).get());
        GoalDao instance = new GoalDao();
        instance.addGoal(goal);
        Assertions.assertFalse(instance.deleteGoal(1000L));
    }

    @Test
    @Order(9)
    public void findByUserIdSuccess(){
        log.info("findByUserIdSuccess");
        GoalDao instance = new GoalDao();
        Set all =  instance.findByUserId(1L);
        log.info(all);
        Assertions.assertNotNull(all);
    }

    @Test
    @Order(10)
    public void findByUserIdFail(){
        log.info("findByUserIdFail");
        GoalDao instance = new GoalDao();
        Set all =  instance.findByUserId(1000L);
        log.info(all);
        Assertions.assertNull(all);
    }

}
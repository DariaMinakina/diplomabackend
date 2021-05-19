package ru.sfedu.diplomabackend.dao.goal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import ru.sfedu.diplomabackend.model.Goal;
import ru.sfedu.diplomabackend.model.Priority;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


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
        Goal goal = new Goal(date, "goal_des", "completed", Priority.PRIORITY_3);
        GoalDao instance = new GoalDao();
        Optional<Long> result = instance.addGoal(goal);
        log.info(result);
        Goal entity = instance.getById(result.get());
        log.debug(result.get());
        Assertions.assertEquals(goal, entity);
    }

    @Test
    @Order(1)
    void addGoalFail() {
        log.info("addGoalFail");
        Date date = new Date();
        Goal goal = new Goal(date, "goal_des", null, Priority.PRIORITY_3);
        GoalDao instance = new GoalDao();
        Optional<Long> result = instance.addGoal(goal);
        log.info(result);
        Assertions.assertNull(goal);
    }

    @Test
    @Order(3)
    public void getByIdGoalSuccess() {
        log.info("getByIdGoalSuccess");
        Date date = new Date();
        Goal goal = new Goal(date, "goal_des", "completed", Priority.PRIORITY_3);
        GoalDao instance = new GoalDao();
        Optional<Long> result = instance.addGoal(goal);
        log.info(result);
        Assertions.assertNotNull(result);
    }

    @Test
    @Order(4)
    public void getByIdFail() {
        log.info("getByIdFail");
        Date date = new Date();
        Goal goal = new Goal(date, "goal_des", "completed", Priority.PRIORITY_3);
        GoalDao instance = new GoalDao();
        Optional<Long> result = instance.addGoal(goal);
        log.info(result);
        Goal entity = instance.getById(100L);
        Assertions.assertNull(entity);
    }

    @Test
    @Order(5)
    public void updateGoalSuccess(){
        log.info("updateGoalSuccess");
        Date date = new Date();
        Goal goal = new Goal(date, "goal_des", "completed", Priority.PRIORITY_3);
        GoalDao instance = new GoalDao();
        Optional<Long> result = instance.addGoal(goal);
        goal.setDescription("changed");
        Assertions.assertTrue(instance.updateGoal(goal));
    }

    @Test
    @Order(6)
    public void updateGoalFail(){
        log.info("updateGoalFail");
        Date date = new Date();
        Goal goal = new Goal(date, "goal_des", "completed", Priority.PRIORITY_3);
        GoalDao instance = new GoalDao();
        goal.setDescription(null);
        Assertions.assertFalse(instance.updateGoal(goal));
    }

    @Test
    @Order(7)
    public void deleteEntitySuccess(){
        log.info("deleteEntitySuccess");
        Date date = new Date();
        Goal goal = new Goal(date, "goal_des", "completed", Priority.PRIORITY_3);
        GoalDao instance = new GoalDao();
        Optional<Long> result = instance.addGoal(goal);
        Assertions.assertTrue(instance.deleteGoal(result.get()));
    }

    @Test
    @Order(8)
    public void deleteEntityFail(){
        log.info("deleteEntityFail");
        Date date = new Date();
        Goal goal = new Goal(date, "goal_des", "completed", Priority.PRIORITY_3);
        GoalDao instance = new GoalDao();
        Optional<Long> result = instance.addGoal(goal);
        Goal entity = instance.getById(100L);
        Assertions.assertFalse(instance.deleteGoal(entity.getId()));
    }

    @Test
    @Order(9)
    public void getAllSuccess(){
        log.info("getAllSuccess");
        GoalDao instance = new GoalDao();
        List all = new ArrayList();
        all = instance.getGoals();
        log.info(all);
        Assertions.assertNotNull(all);
    }

}
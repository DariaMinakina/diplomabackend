package ru.sfedu.diplomabackend.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.sfedu.diplomabackend.model.Goal;
import ru.sfedu.diplomabackend.model.Priority;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class GoalDaoTest {

    private static Logger log = LogManager.getLogger(GoalDaoTest.class);

    public GoalDaoTest(){}


    @Test
    void addGoal() {
        log.info("addEntitySuccess");
        Date date = new Date();
        Goal goal = new Goal(date, "goal_des", "completed", Priority.PRIORITY_3);
        GoalDao instance = new GoalDao();
        Optional<Long> result = instance.addGoal(goal);
        log.info(result);
        Goal entity = instance.getById(result.get());
        log.debug(result.get());
        Assertions.assertEquals(goal, entity);
    }
}
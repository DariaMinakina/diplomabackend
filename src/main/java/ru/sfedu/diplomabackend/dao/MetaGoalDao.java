package ru.sfedu.diplomabackend.dao;

import ru.sfedu.diplomabackend.model.Goal;

import java.util.List;
import java.util.Optional;

public interface MetaGoalDao {

    Goal getById(Long id);

    Optional<Long> addGoal (Goal goal);

    boolean updateGoal (Goal goal);

    boolean deleteGoal (Long id);

    List getGoals();

}

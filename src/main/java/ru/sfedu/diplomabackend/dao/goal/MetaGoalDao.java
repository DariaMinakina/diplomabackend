package ru.sfedu.diplomabackend.dao.goal;

import ru.sfedu.diplomabackend.model.Goal;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface MetaGoalDao {

    Optional<Goal> getGoalById(Long id);

    boolean addGoal (Goal goal);

    boolean updateGoal (Goal goal);

    boolean deleteGoal (Long id);

    Set findByUserId(Long userId);

}

package ru.sfedu.diplomabackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sfedu.diplomabackend.dao.GoalDao;
import ru.sfedu.diplomabackend.model.Goal;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class GoalService implements MetaGoalService{

    @Autowired
    private GoalDao goalDao;

    @Override
    public Goal getById(Long id) {
        return goalDao.getById(id);
    }

    @Override
    public Optional<Long> addGoal(Goal goal) {
        return goalDao.addGoal(goal);
    }

    @Override
    public boolean updateGoal(Goal goal) {
        return goalDao.updateGoal(goal);
    }

    @Override
    public boolean deleteGoal(Long id) {
        return goalDao.deleteGoal(id);
    }

    @Override
    public List getGoals() {
        return goalDao.getGoals();
    }
}

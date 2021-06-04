package ru.sfedu.diplomabackend.service.goal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sfedu.diplomabackend.dao.goal.GoalDao;
import ru.sfedu.diplomabackend.model.Goal;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
@Transactional
public class GoalService implements MetaGoalService{

    @Autowired
    private GoalDao goalDao;

    @Override
    public Optional<Goal> getGoalById(Long id) {
        return goalDao.getGoalById(id);
    }

    @Override
    public boolean addGoal(Goal goal) {
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
    public Set findByUserId(Long userId) {
        return goalDao.findByUserId(userId);
    }


}

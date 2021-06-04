package ru.sfedu.diplomabackend.dao.goal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.sfedu.diplomabackend.dao.user.UserDao;
import ru.sfedu.diplomabackend.model.Goal;
import ru.sfedu.diplomabackend.utils.HibernateUtil;
import static ru.sfedu.diplomabackend.Constants.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class GoalDao implements MetaGoalDao{

    private static Logger log = LogManager.getLogger(GoalDao.class);

    @Autowired
    private SessionFactory sessionFactory;

    public Session getSession() {
        sessionFactory = HibernateUtil.getSessionFactory();
        return sessionFactory.openSession();
    }

    @Override
    public Optional<Goal> getGoalById(Long id) {
        try {
            Session session = this.getSession();
            Goal goal = session.get(Goal.class, id);
            session.close();
            if (goal == null){
                log.error(NOT_FOUND_CONST);
                return Optional.empty();
            }
            else {
                log.info(FOUND_CONST);
                return Optional.of(goal);
            }
        }
        catch (Exception e){
            log.error(e);
            return Optional.empty();
        }
    }

    @Override
    public boolean addGoal(Goal goal) {
        try {
            if (goal != null){
                Session session = this.getSession();
                Transaction transaction = session.beginTransaction();
                Long id = (Long) session.save(goal);
                transaction.commit();
                session.close();
                log.info(ADDED_CONST);
                return true;
            }
            else {
                log.error(NOT_ADDED_CONST);
                return false;
            }
        }
        catch (Exception e){
            log.error(e);
            return false;
        }
    }

    @Override
    public boolean updateGoal(Goal goal) {
        try {
            if (goal != null){
                Session session = this.getSession();
                Transaction transaction = session.beginTransaction();
                session.update(goal);
                transaction.commit();
                session.close();
                log.info(UPDATED_CONST);
                return true;
            }
            else {
                log.error(NOT_UPDATED_CONST);
                return false;
            }
        }
        catch (Exception e){
            log.error(e);
            return false;
        }
    }

    @Override
    public boolean deleteGoal(Long id) {
        try {
            Goal goal = getGoalById(id).get();
            if (goal != null){
                Session session = this.getSession();
                Transaction transaction = session.beginTransaction();
                session.delete(goal);
                transaction.commit();
                session.close();
                log.info(DELETED_CONST);
                return true;
            }
            else {
                log.error(NOT_DELETED_CONST);
                return false;
            }
        }
        catch (Exception e){
            log.error(e);
            return false;
        }
    }


    @Override
    public Set findByUserId(Long userId){
        try {
            if (!new UserDao().getById(userId).isEmpty())
            return (Set) getSession().createQuery("from Goal where userss.id =:userId").setParameter("userId", userId).stream().collect(Collectors.toSet());
            else return null;
        }
        catch (Exception e){
            log.error(e);
            return null;
        }
    }

}

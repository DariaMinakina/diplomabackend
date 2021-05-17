package ru.sfedu.diplomabackend.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.sfedu.diplomabackend.model.Goal;
import ru.sfedu.diplomabackend.utils.HibernateUtil;
import static ru.sfedu.diplomabackend.Constants.*;

import java.util.List;
import java.util.Optional;

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
    public Goal getById(Long id) {
        try {
            Session session = this.getSession();
            Goal goal = (Goal) session.get(Goal.class, id);
            session.close();
            if (goal == null){
                log.error(NOT_FOUND_CONST);
                return null;
            }
            else {
                log.info(FOUND_CONST);
                return goal;
            }
        }
        catch (Exception e){
            log.error(e);
            return null;
        }
    }

    @Override
    public Optional<Long> addGoal(Goal goal) {
        try {
            if (goal != null){
                Session session = this.getSession();
                Transaction transaction = session.beginTransaction();
                Long id = (Long) session.save(goal);
                transaction.commit();
                session.close();
                log.info(ADDED_CONST);
                return Optional.of(id);
            }
            else {
                log.error(NOT_ADDED_CONST);
                return Optional.empty();
            }
        }
        catch (Exception e){
            log.error(e);
            return Optional.empty();
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
            Goal goal = getById(id);
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
    @SuppressWarnings("unchecked")
    public List getGoals() {
        return getSession().createQuery("from Goal").list();
    }
}

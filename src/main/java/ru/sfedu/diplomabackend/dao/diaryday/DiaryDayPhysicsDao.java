package ru.sfedu.diplomabackend.dao.diaryday;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.sfedu.diplomabackend.dao.user.UserDao;
import ru.sfedu.diplomabackend.model.DiaryDayPhysics;
import ru.sfedu.diplomabackend.utils.HibernateUtil;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static ru.sfedu.diplomabackend.Constants.*;

@Repository
public class DiaryDayPhysicsDao implements MetaDiaryDayPhysicsDao{

    private static Logger log = LogManager.getLogger(DiaryDayPhysicsDao.class);

    @Autowired
    private SessionFactory sessionFactory;

    public Session getSession() {
        sessionFactory = HibernateUtil.getSessionFactory();
        return sessionFactory.openSession();
    }

    @Override
    public Optional<DiaryDayPhysics> getByIdDiaryDayPhysics(Long id) {
        try {
            Session session = this.getSession();
            DiaryDayPhysics diaryDayPhysics = session.get(DiaryDayPhysics.class, id);
            session.close();
            if (diaryDayPhysics == null){
                log.error(NOT_FOUND_CONST);
                return Optional.empty();
            }
            else {
                log.info(FOUND_CONST);
                return Optional.of(diaryDayPhysics);
            }
        }
        catch (Exception e){
            log.error(e);
            return Optional.empty();
        }
    }

    @Override
    public boolean addDiaryDayPhysics(DiaryDayPhysics diaryDayPhysics) {
        try {
            if (diaryDayPhysics != null){
                Session session = this.getSession();
                Transaction transaction = session.beginTransaction();
                session.save(diaryDayPhysics);
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
    public boolean updateDiaryDayPhysics(DiaryDayPhysics diaryDayPhysics) {
        try {
            if (diaryDayPhysics != null){
                Session session = this.getSession();
                Transaction transaction = session.beginTransaction();
                session.update(diaryDayPhysics);
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
    public boolean deleteDiaryDayPhysics(Long id) {
        try {
            DiaryDayPhysics diaryDayPhysics = getByIdDiaryDayPhysics(id).get();
            if (diaryDayPhysics != null){
                Session session = this.getSession();
                Transaction transaction = session.beginTransaction();
                session.delete(diaryDayPhysics);
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
    public Set findDiaryDayPhysicsByUserId(Long userId) {
        try {
            if (!new UserDao().getById(userId).isEmpty())
                return (Set) getSession().createQuery("from DiaryDayPhysics where users.id =:userId").setParameter("userId", userId).stream().collect(Collectors.toSet());
            else return null;
        }
        catch (Exception e){
            log.error(e);
            return null;
        }
    }
}

package ru.sfedu.diplomabackend.dao.diaryday;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.sfedu.diplomabackend.model.DiaryDayPhysics;
import ru.sfedu.diplomabackend.utils.HibernateUtil;

import java.util.List;
import java.util.Optional;

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
    public DiaryDayPhysics getByIdDiaryDayPhysics(Long id) {
        try {
            Session session = this.getSession();
            DiaryDayPhysics diaryDayPhysics = session.get(DiaryDayPhysics.class, id);
            session.close();
            if (diaryDayPhysics == null){
                log.error(NOT_FOUND_CONST);
                return null;
            }
            else {
                log.info(FOUND_CONST);
                return diaryDayPhysics;
            }
        }
        catch (Exception e){
            log.error(e);
            return null;
        }
    }

    @Override
    public Optional<Long> addDiaryDayPhysics(DiaryDayPhysics diaryDayPhysics) {
        try {
            if (diaryDayPhysics != null){
                Session session = this.getSession();
                Transaction transaction = session.beginTransaction();
                Long id = (Long) session.save(diaryDayPhysics);
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
            DiaryDayPhysics diaryDayPhysics = getByIdDiaryDayPhysics(id);
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
    @SuppressWarnings("unchecked")
    public List getDiaryDayPhysics() {
        return getSession().createQuery("from DiaryDayPhysics").list();
    }
}

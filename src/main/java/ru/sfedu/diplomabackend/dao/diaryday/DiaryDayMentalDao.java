package ru.sfedu.diplomabackend.dao.diaryday;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;
import ru.sfedu.diplomabackend.dao.user.UserDao;
import ru.sfedu.diplomabackend.model.DiaryDayMental;
import ru.sfedu.diplomabackend.model.Goal;
import ru.sfedu.diplomabackend.utils.HibernateUtil;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static ru.sfedu.diplomabackend.Constants.*;

@Repository
public class DiaryDayMentalDao implements MetaDiaryDayMentalDao {

    private static Logger log = LogManager.getLogger(DiaryDayMentalDao.class);

    @Autowired
    private SessionFactory sessionFactory;

    public Session getSession() {
        sessionFactory = HibernateUtil.getSessionFactory();
        return sessionFactory.openSession();
    }

    @Override
    public Optional<DiaryDayMental> getByIdDiaryDayMental(Long id) {
        try {
            Session session = this.getSession();
            DiaryDayMental diaryDayMental = session.get(DiaryDayMental.class, id);
            session.close();
            if (diaryDayMental == null){
                log.error(NOT_FOUND_CONST);
                return Optional.empty();
            }
            else {
                log.info(FOUND_CONST);
                return Optional.of(diaryDayMental);
            }
        }
        catch (Exception e){
            log.error(e);
            return Optional.empty();
        }
    }

    @Override
    public boolean addDiaryDayMental(DiaryDayMental diaryDayMental) {
        try {
            if (diaryDayMental != null){
                Session session = this.getSession();
                Transaction transaction = session.beginTransaction();
                Long id = (Long) session.save(diaryDayMental);
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
    public boolean updateDiaryDayMental(DiaryDayMental diaryDayMental) {
        try {
            if (diaryDayMental != null){
                Session session = this.getSession();
                Transaction transaction = session.beginTransaction();
                session.update(diaryDayMental);
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
    public boolean deleteDiaryDayMental(Long id) {
        try {
            DiaryDayMental diaryDayMental = getByIdDiaryDayMental(id).get();
            if (diaryDayMental != null){
                Session session = this.getSession();
                Transaction transaction = session.beginTransaction();
                session.delete(diaryDayMental);
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
    public Set findDiaryDayMentalByUserId(Long userId) {
        try {
            if (!new UserDao().getById(userId).isEmpty())
                return (Set) getSession().createQuery("from DiaryDayMental where users.id =:userId").setParameter("userId", userId).stream().collect(Collectors.toSet());
            else return null;
        }
        catch (Exception e){
            log.error(e);
            return null;
        }
    }
}

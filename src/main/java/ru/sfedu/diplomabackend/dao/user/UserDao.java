package ru.sfedu.diplomabackend.dao.user;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import ru.sfedu.diplomabackend.model.User;
import ru.sfedu.diplomabackend.utils.HibernateUtil;

import java.util.List;
import java.util.Optional;

import static ru.sfedu.diplomabackend.Constants.*;

public class UserDao implements MetaUserDao{

    private static Logger log = LogManager.getLogger(UserDao.class);

    @Autowired
    private SessionFactory sessionFactory;

    public Session getSession() {
        sessionFactory = HibernateUtil.getSessionFactory();
        return sessionFactory.openSession();
    }


    @Override
    public User getById(Long id) {
        try {
            Session session = this.getSession();
            User user = session.get(User.class, id);
            session.close();
            if (user == null){
                log.error(NOT_FOUND_CONST);
                return null;
            }
            else {
                log.info(FOUND_CONST);
                return user;
            }
        }
        catch (Exception e){
            log.error(e);
            return null;
        }
    }

    @Override
    public Optional<Long> addUser(User user) {
        try {
            if (user != null){
                Session session = this.getSession();
                Transaction transaction = session.beginTransaction();
                Long id = (Long) session.save(user);
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
    public boolean updateUser(User user) {
        try {
            if (user != null){
                Session session = this.getSession();
                Transaction transaction = session.beginTransaction();
                session.update(user);
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
    public boolean deleteUser(Long id) {
        try {
            User user = getById(id);
            if (user != null){
                Session session = this.getSession();
                Transaction transaction = session.beginTransaction();
                session.delete(user);
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
    public List getUsers() {
        return getSession().createQuery("from User").list();
    }
}

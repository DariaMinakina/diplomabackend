package ru.sfedu.diplomabackend.dao.diaryday;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import ru.sfedu.diplomabackend.dao.user.UserDao;
import ru.sfedu.diplomabackend.model.DiaryDayPhysics;

import java.util.Date;
import java.util.Optional;
import java.util.Set;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DiaryDayPhysicsDaoTest {

    private static Logger log = LogManager.getLogger(DiaryDayPhysicsDaoTest.class);

    public DiaryDayPhysicsDaoTest(){}

    @Test
    @Order(0)
    void addDiaryDayPhysicsSuccess() {
        log.info("addDiaryDayPhysicsSuccess");
        Date date = new Date();
        DiaryDayPhysics diaryDayPhysics = new DiaryDayPhysics(date, "d", 178, 68);
        DiaryDayPhysicsDao instance = new DiaryDayPhysicsDao();
        diaryDayPhysics.setUsers(new UserDao().getById(1L).get());
        Assertions.assertTrue(instance.addDiaryDayPhysics(diaryDayPhysics));
    }

    @Test
    @Order(1)
    void addDiaryDayPhysicsFail() {
        log.info("addDiaryDayPhysicsFail");
        Date date = new Date();
        DiaryDayPhysics diaryDayPhysics = new DiaryDayPhysics(date, null, 178, 68);
        DiaryDayPhysicsDao instance = new DiaryDayPhysicsDao();
        diaryDayPhysics.setUsers(new UserDao().getById(1L).get());
        Assertions.assertFalse(instance.addDiaryDayPhysics(diaryDayPhysics));
    }

    @Test
    @Order(2)
    void getByIdDiaryDayPhysicsSuccess() {
        log.info("getByIdDiaryDayPhysicsSuccess");
        Date date = new Date();
        DiaryDayPhysics diaryDayPhysics = new DiaryDayPhysics(date, "d", 178, 68);
        DiaryDayPhysicsDao instance = new DiaryDayPhysicsDao();
        diaryDayPhysics.setUsers(new UserDao().getById(1L).get());
        instance.addDiaryDayPhysics(diaryDayPhysics);
        Assertions.assertNotNull(instance.getByIdDiaryDayPhysics(diaryDayPhysics.getId()));
    }

    @Test
    @Order(3)
    void getByIdDiaryDayPhysicsFail() {
        log.info("getByIdDiaryDayPhysicsFail");
        Date date = new Date();
        DiaryDayPhysics diaryDayPhysics = new DiaryDayPhysics(date, "d", 178, 68);
        DiaryDayPhysicsDao instance = new DiaryDayPhysicsDao();
        diaryDayPhysics.setUsers(new UserDao().getById(1L).get());
        instance.addDiaryDayPhysics(diaryDayPhysics);
        Assertions.assertEquals(instance.getByIdDiaryDayPhysics(10000L), Optional.empty());
    }

    @Test
    @Order(4)
    void updateDiaryDayPhysicsSuccess() {
        log.info("updateDiaryDayPhysicsSuccess");
        Date date = new Date();
        DiaryDayPhysics diaryDayPhysics = new DiaryDayPhysics(date, "d", 178, 68);
        DiaryDayPhysicsDao instance = new DiaryDayPhysicsDao();
        diaryDayPhysics.setUsers(new UserDao().getById(1L).get());
        instance.addDiaryDayPhysics(diaryDayPhysics);
        diaryDayPhysics.setHeight(180);
        Assertions.assertTrue(instance.updateDiaryDayPhysics(diaryDayPhysics));
    }

    @Test
    @Order(5)
    void updateDiaryDayPhysicsFail() {
        log.info("updateDiaryDayPhysicsFail");
        Date date = new Date();
        DiaryDayPhysics diaryDayPhysics = new DiaryDayPhysics(date, "d", 178, 68);
        diaryDayPhysics.setUsers(new UserDao().getById(1L).get());
        DiaryDayPhysicsDao instance = new DiaryDayPhysicsDao();
        diaryDayPhysics.setDescription(null);
        Assertions.assertFalse(instance.updateDiaryDayPhysics(diaryDayPhysics));
    }

    @Test
    @Order(6)
    void deleteDiaryDayPhysicsSuccess() {
        log.info("deleteDiaryDayPhysicsSuccess");
        Date date = new Date();
        DiaryDayPhysics diaryDayPhysics = new DiaryDayPhysics(date, "d", 178, 68);
        diaryDayPhysics.setUsers(new UserDao().getById(1L).get());
        DiaryDayPhysicsDao instance = new DiaryDayPhysicsDao();
        instance.addDiaryDayPhysics(diaryDayPhysics);
        Assertions.assertTrue(instance.deleteDiaryDayPhysics(diaryDayPhysics.getId()));
    }

    @Test
    @Order(7)
    void deleteDiaryDayPhysicsFail() {
        log.info("deleteDiaryDayPhysicsFail");
        Date date = new Date();
        DiaryDayPhysics diaryDayPhysics = new DiaryDayPhysics(date, "d", 178, 68);
        DiaryDayPhysicsDao instance = new DiaryDayPhysicsDao();
        diaryDayPhysics.setUsers(new UserDao().getById(1L).get());
        instance.addDiaryDayPhysics(diaryDayPhysics);
        Assertions.assertFalse(instance.deleteDiaryDayPhysics(100L));
    }

    @Test
    @Order(8)
    void findDiaryDayPhysicsByUserIdSuccess() {
        log.info("findDiaryDayPhysicsByUserIdSuccess");
        DiaryDayPhysicsDao instance = new DiaryDayPhysicsDao();
        Set all = instance.findDiaryDayPhysicsByUserId(1L);
        log.info(all);
        Assertions.assertNotNull(all);
    }

    @Test
    @Order(10)
    public void findDiaryDayPhysicsByUserIdFail(){
        log.info("findDiaryDayPhysicsByUserIdFail");
        DiaryDayPhysicsDao instance = new DiaryDayPhysicsDao();
        Set all = instance.findDiaryDayPhysicsByUserId(10000L);
        log.info(all);
        Assertions.assertNull(all);
    }

}
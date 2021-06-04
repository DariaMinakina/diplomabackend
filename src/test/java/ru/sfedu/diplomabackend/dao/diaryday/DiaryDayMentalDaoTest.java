package ru.sfedu.diplomabackend.dao.diaryday;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import ru.sfedu.diplomabackend.dao.user.UserDao;
import ru.sfedu.diplomabackend.model.DiaryDayMental;

import java.util.Date;
import java.util.Optional;
import java.util.Set;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DiaryDayMentalDaoTest {

    private static Logger log = LogManager.getLogger(DiaryDayMentalDaoTest.class);

    public DiaryDayMentalDaoTest(){}

    @Test
    @Order(0)
    void addDiaryDayMentalSuccess() {
        log.info("addDiaryDayMentalSuccess");
        Date date = new Date();
        DiaryDayMental diaryDayMental = new DiaryDayMental(date, "d", 5L, 20L);
        DiaryDayMentalDao instance = new DiaryDayMentalDao();
        diaryDayMental.setUsers(new UserDao().getById(1L).get());
        Assertions.assertTrue(instance.addDiaryDayMental(diaryDayMental));
    }

    @Test
    @Order(1)
    void addDiaryDayMentalFail() {
        log.info("addDiaryDayMentalFail");
        Date date = new Date();
        DiaryDayMental diaryDayMental = new DiaryDayMental(date, null, 5L, 20L);
        DiaryDayMentalDao instance = new DiaryDayMentalDao();
        diaryDayMental.setUsers(new UserDao().getById(1L).get());
        Assertions.assertFalse(instance.addDiaryDayMental(diaryDayMental));
    }

    @Test
    @Order(2)
    void getByIdDiaryDayMentalSuccess() {
        log.info("getByIdDiaryDayMentalSuccess");
        Date date = new Date();
        DiaryDayMental diaryDayMental = new DiaryDayMental(date, "d", 5L, 20L);
        DiaryDayMentalDao instance = new DiaryDayMentalDao();
        diaryDayMental.setUsers(new UserDao().getById(1L).get());
        instance.addDiaryDayMental(diaryDayMental);
        Assertions.assertNotNull(instance.getByIdDiaryDayMental(diaryDayMental.getId()));
    }

    @Test
    @Order(3)
    void getByIdDiaryDayMentalFail() {
        log.info("getByIdDiaryDayMentalFail");
        Date date = new Date();
        DiaryDayMental diaryDayMental = new DiaryDayMental(date, "d", 5L, 20L);
        DiaryDayMentalDao instance = new DiaryDayMentalDao();
        diaryDayMental.setUsers(new UserDao().getById(1L).get());
        instance.addDiaryDayMental(diaryDayMental);
        Assertions.assertEquals(instance.getByIdDiaryDayMental(10000L), Optional.empty());
    }

    @Test
    @Order(4)
    void updateDiaryDayMentalSuccess() {
        log.info("updateDiaryDayMentalSuccess");
        Date date = new Date();
        DiaryDayMental diaryDayMental = new DiaryDayMental(date, "d", 5L, 20L);
        DiaryDayMentalDao instance = new DiaryDayMentalDao();
        diaryDayMental.setUsers(new UserDao().getById(1L).get());
        instance.addDiaryDayMental(diaryDayMental);
        diaryDayMental.setDepressionResult(2L);
        Assertions.assertTrue(instance.updateDiaryDayMental(diaryDayMental));
    }

    @Test
    @Order(5)
    void updateDiaryDayMentalFail() {
        log.info("updateDiaryDayMentalFail");
        Date date = new Date();
        DiaryDayMental diaryDayMental = new DiaryDayMental(date, "d", 5L, 20L);
        DiaryDayMentalDao instance = new DiaryDayMentalDao();
        diaryDayMental.setUsers(new UserDao().getById(1L).get());
        diaryDayMental.setDescription(null);
        Assertions.assertFalse(instance.updateDiaryDayMental(diaryDayMental));
    }

    @Test
    @Order(6)
    void deleteDiaryDayMentalSuccess() {
        log.info("deleteDiaryDayMentalSuccess");
        Date date = new Date();
        DiaryDayMental diaryDayMental = new DiaryDayMental(date, "d", 5L, 20L);
        DiaryDayMentalDao instance = new DiaryDayMentalDao();
        diaryDayMental.setUsers(new UserDao().getById(1L).get());
        instance.addDiaryDayMental(diaryDayMental);
        Assertions.assertTrue(instance.deleteDiaryDayMental(diaryDayMental.getId()));
    }

    @Test
    @Order(7)
    void deleteDiaryDayMentalFail() {
        log.info("deleteDiaryDayMentalFail");
        Date date = new Date();
        DiaryDayMental diaryDayMental = new DiaryDayMental(date, "d", 5L, 20L);
        diaryDayMental.setUsers(new UserDao().getById(1L).get());
        DiaryDayMentalDao instance = new DiaryDayMentalDao();
        instance.addDiaryDayMental(diaryDayMental);
        Assertions.assertFalse(instance.deleteDiaryDayMental(100L));
    }

    @Test
    @Order(8)
    void findDiaryDayMentalByUserIdSuccess() {
        log.info("findDiaryDayMentalByUserIdSuccess");
        DiaryDayMentalDao instance = new DiaryDayMentalDao();
        Set all = instance.findDiaryDayMentalByUserId(1L);
        log.info(all);
        Assertions.assertNotNull(all);
    }


    @Test
    @Order(9)
    public void findDiaryDayMentalByUserIdFail(){
        log.info("findDiaryDayMentalByUserIdFail");
        DiaryDayMentalDao instance = new DiaryDayMentalDao();
        Set all = instance.findDiaryDayMentalByUserId(10000L);
        log.info(all);
        Assertions.assertNull(all);
    }

}
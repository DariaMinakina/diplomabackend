package ru.sfedu.diplomabackend.dao.diaryday;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import ru.sfedu.diplomabackend.model.DiaryDayPhysics;

import java.util.Date;
import java.util.List;
import java.util.Optional;


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
        Optional<Long> result = instance.addDiaryDayPhysics(diaryDayPhysics);
        log.info(result);
        DiaryDayPhysics entity = instance.getByIdDiaryDayPhysics(result.get());
        log.debug(result.get());
        Assertions.assertEquals(diaryDayPhysics, entity);
    }

    @Test
    @Order(1)
    void addDiaryDayPhysicsFail() {
        log.info("addDiaryDayPhysicsFail");
        Date date = new Date();
        DiaryDayPhysics diaryDayPhysics = new DiaryDayPhysics(date, null, 178, 68);
        DiaryDayPhysicsDao instance = new DiaryDayPhysicsDao();
        Optional<Long> result = instance.addDiaryDayPhysics(diaryDayPhysics);
        log.info(result);
        Assertions.assertNull(diaryDayPhysics);
    }

    @Test
    @Order(2)
    void getByIdDiaryDayPhysicsSuccess() {
        log.info("getByIdDiaryDayPhysicsSuccess");
        Date date = new Date();
        DiaryDayPhysics diaryDayPhysics = new DiaryDayPhysics(date, "d", 178, 68);
        DiaryDayPhysicsDao instance = new DiaryDayPhysicsDao();
        Optional<Long> result = instance.addDiaryDayPhysics(diaryDayPhysics);
        log.info(result);
        Assertions.assertNotNull(result);
    }

    @Test
    @Order(3)
    void getByIdDiaryDayPhysicsFail() {
        log.info("getByIdDiaryDayPhysicsFail");
        Date date = new Date();
        DiaryDayPhysics diaryDayPhysics = new DiaryDayPhysics(date, "d", 178, 68);
        DiaryDayPhysicsDao instance = new DiaryDayPhysicsDao();
        Optional<Long> result = instance.addDiaryDayPhysics(diaryDayPhysics);
        log.info(result);
        DiaryDayPhysics entity = instance.getByIdDiaryDayPhysics(100L);
        Assertions.assertNull(entity);
    }

    @Test
    @Order(4)
    void updateDiaryDayPhysicsSuccess() {
        log.info("updateDiaryDayPhysicsSuccess");
        Date date = new Date();
        DiaryDayPhysics diaryDayPhysics = new DiaryDayPhysics(date, "d", 178, 68);
        DiaryDayPhysicsDao instance = new DiaryDayPhysicsDao();
        Optional<Long> result = instance.addDiaryDayPhysics(diaryDayPhysics);
        diaryDayPhysics.setHeight(180);
        Assertions.assertTrue(instance.updateDiaryDayPhysics(diaryDayPhysics));
    }

    @Test
    @Order(5)
    void updateDiaryDayPhysicsFail() {
        log.info("updateDiaryDayPhysicsFail");
        Date date = new Date();
        DiaryDayPhysics diaryDayPhysics = new DiaryDayPhysics(date, "d", 178, 68);
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
        DiaryDayPhysicsDao instance = new DiaryDayPhysicsDao();
        Optional<Long> result = instance.addDiaryDayPhysics(diaryDayPhysics);
        instance.addDiaryDayPhysics(new DiaryDayPhysics(date, "dd", 165, 42));
        Assertions.assertTrue(instance.deleteDiaryDayPhysics(diaryDayPhysics.getId()));
    }

    @Test
    @Order(7)
    void deleteDiaryDayPhysicsFail() {
        log.info("deleteDiaryDayPhysicsFail");
        Date date = new Date();
        DiaryDayPhysics diaryDayPhysics = new DiaryDayPhysics(date, "d", 178, 68);
        DiaryDayPhysicsDao instance = new DiaryDayPhysicsDao();
        Optional<Long> result = instance.addDiaryDayPhysics(diaryDayPhysics);
        Assertions.assertFalse(instance.deleteDiaryDayPhysics(100L));
    }

    @Test
    @Order(8)
    void getDiaryDayPhysics() {
        log.info("getAllSuccess");
        DiaryDayPhysicsDao instance = new DiaryDayPhysicsDao();
        List all = instance.getDiaryDayPhysics();
        log.info(all);
        Assertions.assertNotNull(all);
    }
}
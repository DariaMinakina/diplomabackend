package ru.sfedu.diplomabackend.dao.diaryday;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import ru.sfedu.diplomabackend.model.DiaryDayMental;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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
        Optional<Long> result = instance.addDiaryDayMental(diaryDayMental);
        log.info(result);
        DiaryDayMental entity = instance.getByIdDiaryDayMental(result.get());
        log.debug(result.get());
        Assertions.assertEquals(diaryDayMental, entity);
    }

    @Test
    @Order(1)
    void addDiaryDayMentalFail() {
        log.info("addDiaryDayMentalFail");
        Date date = new Date();
        DiaryDayMental diaryDayMental = new DiaryDayMental(date, null, 5L, 20L);
        DiaryDayMentalDao instance = new DiaryDayMentalDao();
        Optional<Long> result = instance.addDiaryDayMental(diaryDayMental);
        log.info(result);
        Assertions.assertNull(diaryDayMental);
    }

    @Test
    @Order(2)
    void getByIdDiaryDayMentalSuccess() {
        log.info("getByIdDiaryDayMentalSuccess");
        Date date = new Date();
        DiaryDayMental diaryDayMental = new DiaryDayMental(date, "d", 5L, 20L);
        DiaryDayMentalDao instance = new DiaryDayMentalDao();
        Optional<Long> result = instance.addDiaryDayMental(diaryDayMental);
        log.info(result);
        Assertions.assertNotNull(result);
    }

    @Test
    @Order(3)
    void getByIdDiaryDayMentalFail() {
        log.info("getByIdDiaryDayMentalFail");
        Date date = new Date();
        DiaryDayMental diaryDayMental = new DiaryDayMental(date, "d", 5L, 20L);
        DiaryDayMentalDao instance = new DiaryDayMentalDao();
        Optional<Long> result = instance.addDiaryDayMental(diaryDayMental);
        log.info(result);
        DiaryDayMental entity = instance.getByIdDiaryDayMental(100L);
        Assertions.assertNull(entity);
    }

    @Test
    @Order(4)
    void updateDiaryDayMentalSuccess() {
        log.info("updateDiaryDayMentalSuccess");
        Date date = new Date();
        DiaryDayMental diaryDayMental = new DiaryDayMental(date, "d", 5L, 20L);
        DiaryDayMentalDao instance = new DiaryDayMentalDao();
        Optional<Long> result = instance.addDiaryDayMental(diaryDayMental);
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
        Optional<Long> result = instance.addDiaryDayMental(diaryDayMental);
        instance.addDiaryDayMental(new DiaryDayMental(date, "dd", 5L, 20L));
        Assertions.assertTrue(instance.deleteDiaryDayMental(diaryDayMental.getId()));
    }

    @Test
    @Order(7)
    void deleteDiaryDayMentalFail() {
        log.info("deleteDiaryDayMentalFail");
        Date date = new Date();
        DiaryDayMental diaryDayMental = new DiaryDayMental(date, "d", 5L, 20L);
        DiaryDayMentalDao instance = new DiaryDayMentalDao();
        Optional<Long> result = instance.addDiaryDayMental(diaryDayMental);
        Assertions.assertFalse(instance.deleteDiaryDayMental(100L));
    }

    @Test
    @Order(8)
    void getDiaryDayMental() {
        log.info("getDiaryDayMental");
        DiaryDayMentalDao instance = new DiaryDayMentalDao();
        List all = instance.getDiaryDayMental();
        log.info(all);
        Assertions.assertNotNull(all);
    }
}
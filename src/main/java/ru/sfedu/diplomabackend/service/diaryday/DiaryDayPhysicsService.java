package ru.sfedu.diplomabackend.service.diaryday;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sfedu.diplomabackend.dao.diaryday.DiaryDayPhysicsDao;
import ru.sfedu.diplomabackend.model.DiaryDayPhysics;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
@Transactional
public class DiaryDayPhysicsService implements MetaDiaryDayPhysicsService{

    @Autowired
    private DiaryDayPhysicsDao diaryDayPhysicsDao;

    @Override
    public Optional<DiaryDayPhysics> getByIdDiaryDayPhysics(Long id) {
        return diaryDayPhysicsDao.getByIdDiaryDayPhysics(id);
    }

    @Override
    public boolean addDiaryDayPhysics(DiaryDayPhysics diaryDayPhysics) {
        return diaryDayPhysicsDao.addDiaryDayPhysics(diaryDayPhysics);
    }

    @Override
    public boolean updateDiaryDayPhysics(DiaryDayPhysics diaryDayPhysics) {
        return diaryDayPhysicsDao.updateDiaryDayPhysics(diaryDayPhysics);
    }

    @Override
    public boolean deleteDiaryDayPhysics(Long id) {
        return diaryDayPhysicsDao.deleteDiaryDayPhysics(id);
    }

    @Override
    public Set findDiaryDayPhysicsByUserId(Long userId) {
        return diaryDayPhysicsDao.findDiaryDayPhysicsByUserId(userId);
    }
}

package ru.sfedu.diplomabackend.dao.diaryday;

import ru.sfedu.diplomabackend.model.DiaryDayPhysics;

import java.util.List;
import java.util.Optional;

public interface MetaDiaryDayPhysicsDao {

    DiaryDayPhysics getByIdDiaryDayPhysics(Long id);

    Optional<Long> addDiaryDayPhysics (DiaryDayPhysics diaryDayPhysics);

    boolean updateDiaryDayPhysics (DiaryDayPhysics diaryDayPhysics);

    boolean deleteDiaryDayPhysics (Long id);

    List getDiaryDayPhysics();

}

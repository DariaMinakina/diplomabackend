package ru.sfedu.diplomabackend.service.diaryday;

import ru.sfedu.diplomabackend.model.DiaryDayPhysics;

import java.util.List;
import java.util.Optional;

public interface MetaDiaryDayPhysicsService {

    DiaryDayPhysics getByIdDiaryDayPhysics(Long id);

    Optional<Long> addDiaryDayPhysics (DiaryDayPhysics diaryDayPhysics);

    boolean updateDiaryDayPhysics (DiaryDayPhysics diaryDayPhysics);

    boolean deleteDiaryDayPhysics (Long id);

    List getDiaryDayPhysics();

}

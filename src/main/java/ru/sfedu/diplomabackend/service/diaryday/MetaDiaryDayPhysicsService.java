package ru.sfedu.diplomabackend.service.diaryday;

import org.springframework.stereotype.Service;
import ru.sfedu.diplomabackend.model.DiaryDayPhysics;

import java.util.List;
import java.util.Optional;
import java.util.Set;


public interface MetaDiaryDayPhysicsService {

    Optional<DiaryDayPhysics> getByIdDiaryDayPhysics(Long id);

    boolean addDiaryDayPhysics (DiaryDayPhysics diaryDayPhysics);

    boolean updateDiaryDayPhysics (DiaryDayPhysics diaryDayPhysics);

    boolean deleteDiaryDayPhysics (Long id);

    Set findDiaryDayPhysicsByUserId(Long userId);

}

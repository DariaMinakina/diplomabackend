package ru.sfedu.diplomabackend.service.diaryday;

import org.springframework.stereotype.Service;
import ru.sfedu.diplomabackend.model.DiaryDayMental;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface MetaDiaryDayMentalService {

    Optional<DiaryDayMental> getByIdDiaryDayMental(Long id);

    boolean addDiaryDayMental (DiaryDayMental diaryDayMental);

    boolean updateDiaryDayMental (DiaryDayMental diaryDayMental);

    boolean deleteDiaryDayMental (Long id);

    Set findDiaryDayMentalByUserId(Long userId);

}

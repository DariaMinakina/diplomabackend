package ru.sfedu.diplomabackend.dao.diaryday;

import ru.sfedu.diplomabackend.model.DiaryDayMental;

import java.util.List;
import java.util.Optional;

public interface MetaDiaryDayMentalDao {

    DiaryDayMental getByIdDiaryDayMental(Long id);

    Optional<Long> addDiaryDayMental (DiaryDayMental diaryDayMental);

    boolean updateDiaryDayMental (DiaryDayMental diaryDayMental);

    boolean deleteDiaryDayMental (Long id);

    List getDiaryDayMental();


}

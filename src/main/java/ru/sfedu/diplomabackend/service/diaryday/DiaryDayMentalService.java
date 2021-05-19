package ru.sfedu.diplomabackend.service.diaryday;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sfedu.diplomabackend.dao.diaryday.DiaryDayMentalDao;
import ru.sfedu.diplomabackend.model.DiaryDayMental;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class DiaryDayMentalService implements MetaDiaryDayMentalService{

    @Autowired
    private DiaryDayMentalDao diaryDayMentalDao;

    @Override
    public DiaryDayMental getByIdDiaryDayMental(Long id) {
        return diaryDayMentalDao.getByIdDiaryDayMental(id);
    }

    @Override
    public Optional<Long> addDiaryDayMental(DiaryDayMental diaryDayMental) {
        return diaryDayMentalDao.addDiaryDayMental(diaryDayMental);
    }

    @Override
    public boolean updateDiaryDayMental(DiaryDayMental diaryDayMental) {
        return diaryDayMentalDao.updateDiaryDayMental(diaryDayMental);
    }

    @Override
    public boolean deleteDiaryDayMental(Long id) {
        return diaryDayMentalDao.deleteDiaryDayMental(id);
    }

    @Override
    public List getDiaryDayMental() {
        return diaryDayMentalDao.getDiaryDayMental();
    }
}

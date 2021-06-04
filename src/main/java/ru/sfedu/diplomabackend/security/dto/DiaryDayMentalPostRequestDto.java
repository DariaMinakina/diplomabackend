package ru.sfedu.diplomabackend.security.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.sfedu.diplomabackend.model.DiaryDayMental;
import ru.sfedu.diplomabackend.model.DiaryDayPhysics;

import java.util.Date;

@Data
@NoArgsConstructor
public class DiaryDayMentalPostRequestDto {

    private String description;
    private long depressionResult;
    private long burnoutResult;

    public DiaryDayMentalPostRequestDto(DiaryDayMental diaryDayMental) {
        description = diaryDayMental.getDescription();
        depressionResult = diaryDayMental.getDepressionResult();
        depressionResult = diaryDayMental.getBurnoutResult();;
    }

    public DiaryDayMental toDiaryDayMental() {
        DiaryDayMental diaryDayMental = new DiaryDayMental();
        diaryDayMental.setDescription(description);
        diaryDayMental.setDepressionResult(depressionResult);
        diaryDayMental.setBurnoutResult(burnoutResult);
        diaryDayMental.setCreated(new Date());
        return diaryDayMental;
    }

}

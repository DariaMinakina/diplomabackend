package ru.sfedu.diplomabackend.security.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.sfedu.diplomabackend.model.DiaryDayPhysics;

import java.util.Date;

@Data
@NoArgsConstructor
public class DiaryDayPostPhysicsRequestDto {

    private String description;
    private int height;
    private double weight;

    public DiaryDayPostPhysicsRequestDto(DiaryDayPhysics diaryDayPhysics) {
        description = diaryDayPhysics.getDescription();
        height = diaryDayPhysics.getHeight();
        weight = diaryDayPhysics.getWeight();;
    }

    public DiaryDayPhysics toDiaryDayPhysics() {
        DiaryDayPhysics diaryDayPhysics = new DiaryDayPhysics();
        diaryDayPhysics.setDescription(description);
        diaryDayPhysics.setHeight(height);
        diaryDayPhysics.setWeight(weight);
        diaryDayPhysics.setCreated(new Date());
        return diaryDayPhysics;
    }
}

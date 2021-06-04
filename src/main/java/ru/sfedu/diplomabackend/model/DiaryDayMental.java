package ru.sfedu.diplomabackend.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table (name = "diary_day_mental")
@ToString
@Getter
@Setter
//@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class DiaryDayMental extends DiaryDay {

    @Column
    private long depressionResult;

    @Column
    private long burnoutResult;

    public DiaryDayMental(Date created, String description, long depressionResult, long burnoutResult) {
        super(created, description);
        this.depressionResult = depressionResult;
        this.burnoutResult = burnoutResult;
    }
}

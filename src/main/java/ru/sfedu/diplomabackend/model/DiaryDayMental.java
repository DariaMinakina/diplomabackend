package ru.sfedu.diplomabackend.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table
@ToString
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class DiaryDayMental extends DiaryDay {

    private long depressionResult;

    private long burnoutResult;

    public DiaryDayMental(Date created, String description, long depressionResult, long burnoutResult) {
        super(created, description);
        this.depressionResult = depressionResult;
        this.burnoutResult = burnoutResult;
    }
}

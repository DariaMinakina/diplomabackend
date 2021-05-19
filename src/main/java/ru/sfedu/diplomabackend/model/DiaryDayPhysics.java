package ru.sfedu.diplomabackend.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class DiaryDayPhysics extends DiaryDay{

    private int height;

    private double weight;

    public DiaryDayPhysics(Date created, String description, int height, double weight) {
        super(created, description);
        this.height = height;
        this.weight = weight;
    }
}

package ru.sfedu.diplomabackend.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table (name = "diary_day_physics")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
//@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(callSuper = true)
public class DiaryDayPhysics extends DiaryDay{

    @Column (nullable = false)
    private int height;

    @Column(nullable = false)
    private double weight;

    public DiaryDayPhysics(Date created, String description, int height, double weight) {
        super(created, description);
        this.height = height;
        this.weight = weight;
    }
}

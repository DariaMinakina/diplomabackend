package ru.sfedu.diplomabackend.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Goal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Date created;

    private String description;

    private String status;

    private Priority priority;

    public Goal(Date created, String description, String status, Priority priority) {
        this.created = created;
        this.description = description;
        this.status = status;
        this.priority = priority;
    }
}

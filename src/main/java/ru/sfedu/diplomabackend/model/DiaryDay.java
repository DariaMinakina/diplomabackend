package ru.sfedu.diplomabackend.model;


import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Inheritance (strategy = InheritanceType.JOINED)
public class DiaryDay implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Date created;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY, cascade =  {CascadeType.PERSIST})
    @JoinColumn(name = "user")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    public DiaryDay(Date created, String description) {
        this.created = created;
        this.description = description;
    }
}

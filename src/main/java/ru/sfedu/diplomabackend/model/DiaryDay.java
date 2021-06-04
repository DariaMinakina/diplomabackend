package ru.sfedu.diplomabackend.model;


import com.fasterxml.jackson.annotation.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "diary_day")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Inheritance (strategy = InheritanceType.JOINED)
public class DiaryDay implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column
    private Long id;

    @Column (nullable = false)
    private Date created;

    @Column (nullable = false)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @ToString.Exclude
    private User users;

    public DiaryDay(Date created, String description) {
        this.created = created;
        this.description = description;
    }

   //  @JsonBackReference
    @JsonIgnore
    public User getUsers() {
        return users;
    }

}

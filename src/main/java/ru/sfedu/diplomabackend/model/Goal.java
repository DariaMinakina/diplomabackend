package ru.sfedu.diplomabackend.model;

import com.fasterxml.jackson.annotation.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Goal implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column
    private Long id;

    @Column(nullable = false)
    private Date created;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Priority priority;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @ToString.Exclude
    private User userss;

    public Goal(Date created, String description, Priority priority) {
        this.created = created;
        this.description = description;
        this.priority = priority;
    }

    public Goal(Long id, Date created, String description, Priority priority) {
        this.id = id;
        this.created = created;
        this.description = description;
        this.priority = priority;
    }

    //@JsonBackReference
    @JsonIgnore
    public User getUserss() {
        return userss;
    }
}

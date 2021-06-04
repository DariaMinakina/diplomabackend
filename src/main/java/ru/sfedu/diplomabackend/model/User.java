package ru.sfedu.diplomabackend.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;


@Entity
@Table (name = "user_table")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column
    private Long id;

    @Column (nullable = false)
    private String email;

    @Column (nullable = false)
    private String password;

    @Column (name = "first_name", nullable = false)
    private String firstName;

    @Column (name = "last_name", nullable = false)
    private String lastName;

    @Column (nullable = false)
    private Date created = new Date();


    @OneToMany(mappedBy = "userss", fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Goal> goalSet = new HashSet<>();

    @OneToMany(mappedBy = "users", fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<DiaryDay> diaryDays = new HashSet<>();

    public User(String email, String password, String firstName, String lastName) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

   /* @JsonManagedReference
    public Set<Goal> getGoalSet() {
        return goalSet;
    }

    @JsonManagedReference
    public Set<DiaryDay> getDiaryDays() {
        return diaryDays;
    }*/

  /*  public Set<Goal> getGoalSet() {
        Set<Goal> goals = new HashSet<>();
        for (Goal goal: goalSet) {
            goals.add(new Goal(goal.getId(), goal.getCreated(), goal.getDescription(), goal.getPriority()));
        }
        return goals;
    }*/

}

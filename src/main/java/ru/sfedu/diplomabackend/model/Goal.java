package ru.sfedu.diplomabackend.model;

import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Goal implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private Date created;

    @NotNull
    private String description;

    @NotNull
    private String status;

    @NotNull
    private Priority priority;

    @ManyToOne(fetch = FetchType.LAZY, cascade =  {CascadeType.PERSIST})
    @JoinColumn(name = "user")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    public Goal(Date created, String description, String status, Priority priority) {
        this.created = created;
        this.description = description;
        this.status = status;
        this.priority = priority;
    }
}

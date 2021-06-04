package ru.sfedu.diplomabackend.security.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.sfedu.diplomabackend.model.Goal;
import ru.sfedu.diplomabackend.model.Priority;

import java.util.Date;

@Data
@NoArgsConstructor
public class GoalPostRequestDto {

    private String description;
    private Priority priority;

    public GoalPostRequestDto(Goal goal) {
        description = goal.getDescription();
        priority = goal.getPriority();
    }

    public Goal toGoal() {
        Goal goal = new Goal();
        goal.setDescription(description);
        goal.setPriority(priority);
        goal.setCreated(new Date());
        return goal;
    }

}

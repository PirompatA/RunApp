package org.springbootproject1.runnerz.run;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

public record Run(
        Integer id,
        @NotEmpty
        String title,
        LocalDateTime startedOn,
        LocalDateTime completedOn,
        @Positive
        Integer miles,

        Location location
) {
    public Run {
        if(!completedOn.isAfter(startedOn)){
            throw new IllegalArgumentException("Completed On must be after Started On");
        }
    }

    @Override
    public Integer id() {
        return id;
    }

    @Override
    public String title() {
        return title;
    }

    @Override
    public LocalDateTime startedOn() {
        return startedOn;
    }

    @Override
    public LocalDateTime completedOn() {
        return completedOn;
    }

    @Override
    public Integer miles() {
        return miles;
    }

    @Override
    public Location location() {
        return location;
    }
}

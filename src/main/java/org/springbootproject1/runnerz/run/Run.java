package org.springbootproject1.runnerz.run;

import java.time.LocalDateTime;

public record Run(
        Integer id,
        String title,
        LocalDateTime startedOn,
        LocalDateTime completedOn,
        Integer miles,
        Location location
) {
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

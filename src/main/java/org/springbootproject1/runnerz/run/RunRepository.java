package org.springbootproject1.runnerz.run;

import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public class RunRepository {
    private List<Run> runs = new ArrayList<>();

    List<Run> findAll() {
        return runs;
    }

    Optional<Run> findById(Integer id) {
        return runs.stream()
                .filter(run -> run.id() == id)
                .findFirst();
    }

    Run createRun(Run run) {
        runs.add(run);
        return run;
    }

//    void updateRun(Integer id, Run run) {
//        Optional<Run> existingRun = findById(id);
//        if (existingRun.isPresent()) {
//            runs.set(runs.indexOf(existingRun.get()), run);
//            return null;
//        }
//    }
    @PostConstruct
    private void init(){
        runs.add(new Run(1,"11", LocalDateTime.now(), LocalDateTime.now().plus(30, ChronoUnit.MINUTES), 3,Location.INDOOR));
        runs.add(new Run(2,"22", LocalDateTime.now(), LocalDateTime.now().plus(60, ChronoUnit.MINUTES), 6,Location.INDOOR));
        runs.add(new Run(3,"33", LocalDateTime.now(), LocalDateTime.now().plus(60, ChronoUnit.MINUTES), 6,Location.INDOOR));

    }

}

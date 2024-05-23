package org.springbootproject1.runnerz.run;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;

import java.util.List;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@JdbcTest
@Import(JdbcRunRepository.class)
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
class JdbcRunRepositoryTest {

    @Autowired
    JdbcRunRepository repository;

    @BeforeEach
    void setUp() {
        repository.createRun(new Run(1,
                "Monday Morning Run",
                LocalDateTime.now(),
                LocalDateTime.now().plus(30, ChronoUnit.MINUTES),
                3,
                Location.INDOOR,
                null));

        repository.createRun(new Run(2,
                "Wednesday Evening Run",
                LocalDateTime.now(),
                LocalDateTime.now().plus(60, ChronoUnit.MINUTES),
                6,
                Location.INDOOR,
                null));
    }

    @Test
    void shouldFindAllRuns() {
        java.util.List<Run> runs = repository.findAll();
        assertEquals(2, runs.size());
    }

    @Test
    void shouldFindRunWithValidId() {
        Run run = repository.findById(1).get();
        assertEquals("Monday Morning Run", run.title());
    }

    @Test
    void shouldNotFindRunWithInvalidId() {
        var run = repository.findById(3);
        assertTrue(run.isEmpty());
    }

    @Test
    void shouldCreateNewRun() {
        repository.createRun(new Run(3,
                "Friday Morning Run",
                LocalDateTime.now(),
                LocalDateTime.now().plus(30, ChronoUnit.MINUTES),
                3,
                Location.INDOOR,
                null));
        List<Run> runs = repository.findAll();
        assertEquals(3, runs.size());
    }

    @Test
    void shouldUpdateRun() {
        repository.updateRun(1, new Run(1,
                "Monday Morning Run",
                LocalDateTime.now(),
                LocalDateTime.now().plus(30, ChronoUnit.MINUTES),
                5,
                Location.OUTDOOR,
                null));
        Run run = repository.findById(1).get();
        assertEquals("Monday Morning Run", run.title());
        assertEquals(5, run.miles());
        assertEquals(Location.OUTDOOR, run.location());
    }

    @Test
    void shouldDeleteRun() {
        repository.deleteRun(1);
        List<Run> runs = repository.findAll();
        assertEquals(1, runs.size());
    }

}
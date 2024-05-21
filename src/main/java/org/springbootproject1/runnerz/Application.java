package org.springbootproject1.runnerz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springbootproject1.runnerz.run.Location;
import org.springbootproject1.runnerz.run.Run;
import org.springbootproject1.runnerz.run.RunRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@SpringBootApplication
public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        log.info("Application started successfully");
    }

//    @Bean
//    CommandLineRunner runner(RunRepository runRepository){
//        return args -> {
//            Run run = new Run(3, "Add from bean", LocalDateTime.now(), LocalDateTime.now().plus(1, ChronoUnit.HOURS), 5 , Location.OUTDOOR);
//            runRepository.createRun(run);
//        };
//    }

}

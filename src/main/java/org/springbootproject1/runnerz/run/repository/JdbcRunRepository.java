package org.springbootproject1.runnerz.run.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springbootproject1.runnerz.run.entity.Run;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Repository
public class JdbcRunRepository {

    private static final Logger log = LoggerFactory.getLogger(JdbcRunRepository.class);
    private final JdbcClient jdbcClient;

    public JdbcRunRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public List<Run> findAll(){
        return jdbcClient.sql("select * from run")
                .query(Run.class)
                .list();
    }

    public Optional<Run> findById(Integer id) {
        return jdbcClient.sql("SELECT id,title,started_on,completed_on,miles,location,version FROM Run WHERE id = :id" )
                .param("id", id)
                .query(Run.class)
                .optional();
    }

    public Optional<Run> createRun(Run run) {
        var updated = jdbcClient.sql("INSERT INTO Run(id,title,started_on,completed_on,miles,location) values(?,?,?,?,?,?)")
                .params(List.of(run.id(),run.title(),run.startedOn(),run.completedOn(),run.miles(),run.location().toString()))
                .update();

        Assert.state(updated == 1, "Failed to create run " + run.title());
        return findById(run.id());
    }

    public void updateRun(Integer id, Run run) {
        var updated = jdbcClient.sql("update run set title = ?, started_on = ?, completed_on = ?, miles = ?, location = ? where id = ?")
                .params(List.of(run.title(),run.startedOn(),run.completedOn(),run.miles(),run.location().toString(), id))
                .update();

        Assert.state(updated == 1, "Failed to update run " + run.title());
    }

    public void deleteRun(Integer id) {
        var updated = jdbcClient.sql("delete from run where id = :id")
                .param("id", id)
                .update();

        Assert.state(updated == 1, "Failed to delete run " + id);
    }

    public int count() {
        return jdbcClient.sql("select * from run").query().listOfRows().size();
    }

    public void saveAll(List<Run> runs) {
        runs.stream().forEach(this::createRun);
    }

    public List<Run> findByLocation(String location) {
        return jdbcClient.sql("select * from run where location = :location")
                .param("location", location)
                .query(Run.class)
                .list();
    }

//    private List<Run> runs = new ArrayList<>();
//
//    List<Run> findAll() {
//        return runs;
//    }
//
//    Optional<Run> findById(Integer id) {
//        return runs.stream()
//                .filter(run -> run.id() == id)
//                .findFirst();
//    }
//
//    Run createRun(Run run) {
//        runs.add(run);
//        return run;
//    }
//
//    void updateRun(Integer id, Run run) {
//        Optional<Run> existingRun = findById(id);
//        if (existingRun.isPresent()) {
//            runs.set(runs.indexOf(existingRun.get()), run);
//        }
//    }
//
//    void deleteRun(Integer id){
//        runs.removeIf(run -> run.id().equals(id));
//    }
//    @PostConstruct
//    private void init(){
//        runs.add(new Run(1,"11", LocalDateTime.now(), LocalDateTime.now().plus(30, ChronoUnit.MINUTES), 3,Location.INDOOR));
//        runs.add(new Run(2,"22", LocalDateTime.now(), LocalDateTime.now().plus(60, ChronoUnit.MINUTES), 6,Location.INDOOR));
//        runs.add(new Run(3,"33", LocalDateTime.now(), LocalDateTime.now().plus(60, ChronoUnit.MINUTES), 6,Location.INDOOR));
//
//    }

}

package org.springbootproject1.runnerz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springbootproject1.runnerz.user.entity.User;
import org.springbootproject1.runnerz.user.userClient.UserHttpClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import java.util.List;

@SpringBootApplication
public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        log.info("Application started successfully");
    }

    @Bean
    UserHttpClient userHttpClient(){
        RestClient restClient = RestClient.create("https://jsonplaceholder.typicode.com/");
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(RestClientAdapter.create(restClient)).build();
        return factory.createClient(UserHttpClient.class);
    }
    @Bean
    CommandLineRunner runner(UserHttpClient client){
        return args -> {
          List<User> users = client.findAll();
          System.out.println(users);

          User user = client.findById(1);
          System.out.println(user);
        };
    }
//    @Bean
//    CommandLineRunner runner(RunRepository runRepository){
//        return args -> {
//            Run run = new Run(3, "Add from bean", LocalDateTime.now(), LocalDateTime.now().plus(1, ChronoUnit.HOURS), 5 , Location.OUTDOOR);
//            runRepository.createRun(run);
//        };
//    }

}

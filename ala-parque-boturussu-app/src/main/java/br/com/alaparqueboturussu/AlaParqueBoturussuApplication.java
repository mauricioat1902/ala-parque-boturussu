package br.com.alaparqueboturussu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@ComponentScan({"br.com.alaparqueboturussu", "br.com.alaparqueboturussu.core"})
//@EnableJpaRepositories({"br.com.alaparqueboturussu.core.repository", "br.com.alaparqueboturussu.repository"})
//@EntityScan("br.com.alaboturussu.core.entity")
public class AlaParqueBoturussuApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlaParqueBoturussuApplication.class, args);
    }
}

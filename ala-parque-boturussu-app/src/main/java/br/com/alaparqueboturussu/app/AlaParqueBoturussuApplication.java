package br.com.alaparqueboturussu.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"br.com.alaparqueboturussu.core", "br.com.alaparqueboturussu.app"})
@ComponentScan({"br.com.alaparqueboturussu.core.service", "br.com.alaparqueboturussu.app.service"})
@EnableJpaRepositories({"br.com.alaparqueboturussu.core.repository", "br.com.alaparqueboturussu.app.repository"})
@EntityScan("br.com.alaparqueboturussu.core.entity")
public class AlaParqueBoturussuApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlaParqueBoturussuApplication.class, args);
    }
}

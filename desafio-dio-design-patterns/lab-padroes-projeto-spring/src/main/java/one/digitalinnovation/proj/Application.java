package one.digitalinnovation.proj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Projeto Spring Boot gerado via Spring Initializr.
 * Os seguintes módulos foram selecionados:
 * - Spring Data JPA;
 * - Spring Web;
 * - H2 Database;
 * - OpenFeign.
 *
 *@see <a href="http://localhost:8080/swagger-ui/index.html#/">Swagger Local Host:8080</a>
 *
 * @author Mariana Molina
 */

@EnableFeignClients
@SpringBootApplication
@EnableJpaRepositories
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}

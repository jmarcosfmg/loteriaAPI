package zup.orangetalents.loteriaAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class LoteriaApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoteriaApiApplication.class, args);
	}

}

package br.lecouti.dev.springangulartest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Boot Application class
 * 
 * @author Leandro Coutinho
 */
@SpringBootApplication
public class SpringAngularTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringAngularTestApplication.class, args);
	}

	// Uncomment to initialize the H2 embedded database with some objects
	// @Bean
	// ApplicationRunner init(ClientRepository clientRepo) {
	// return args -> {
	// Client c1 = new Client("Client 1", "1500.10", RiskLevel.C, "Street 1, 1111");
	// Client c2 = new Client("Client 2", "4000.20", RiskLevel.B, "Street 2, 2222");
	// Client c3 = new Client("Client 3", "10000.30", RiskLevel.A, "Street 3,
	// 3333");
	//
	// clientRepo.save(c1);
	// clientRepo.save(c2);
	// clientRepo.save(c3);
	//
	// clientRepo.findAll().forEach(System.out::println);
	// };
	// }
}

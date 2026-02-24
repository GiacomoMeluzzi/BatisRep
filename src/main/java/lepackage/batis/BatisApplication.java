package lepackage.batis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "lepackage")
public class BatisApplication {

	public static void main(String[] args) {
		SpringApplication.run(BatisApplication.class, args);
	}

}

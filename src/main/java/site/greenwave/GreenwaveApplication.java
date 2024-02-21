package site.greenwave;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"site","springsecuritybasic"})
public class GreenwaveApplication {

	public static void main(String[] args) {
		SpringApplication.run(GreenwaveApplication.class, args);
	}

}

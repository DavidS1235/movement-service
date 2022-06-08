package com.nttdata.movementservice;

import com.nttdata.movementservice.client.MovementClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.XADataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class, XADataSourceAutoConfiguration.class})
public class MovementServiceApplication implements CommandLineRunner {

	private final Logger logger = LoggerFactory.getLogger("MovementServiceApplication");

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(MovementServiceApplication.class, args);

		//MovementClient movementClient = context.getBean(MovementClient.class);
	}

	@Override
	public void run(String... args) throws Exception {

	}
}

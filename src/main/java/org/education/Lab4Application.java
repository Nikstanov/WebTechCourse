package org.education;

import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.PropertyConfigurator;
import org.education.config.ApplicationContextHolder;
import org.education.service.UserService;
import org.education.service.exception.ServiceException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@Slf4j
public class Lab4Application {

	public static void main(String[] args) {
		SpringApplication.run(Lab4Application.class, args);

		Properties logProperties = new Properties();
		try {
			logProperties.load(Lab4Application.class.getClassLoader().getResourceAsStream("log4j.properties"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		PropertyConfigurator.configure(logProperties);
//		log.info("start server " + getServletName());

		var scheduler = Executors.newScheduledThreadPool(1);
		scheduler.scheduleAtFixedRate(
				() -> {
					try {
						ApplicationContextHolder.getApplicationContext().getBean(UserService.class).updateRate();
					} catch (ServiceException e) {
						log.error(e.getMessage());
					}
				},
				5,5, TimeUnit.MINUTES);
	}

}

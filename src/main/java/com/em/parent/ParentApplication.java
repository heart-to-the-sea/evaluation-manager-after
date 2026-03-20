package com.em.parent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {
        com.fhs.trans.config.TransServiceConfig.class,
})
public class ParentApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParentApplication.class, args);
	}

}

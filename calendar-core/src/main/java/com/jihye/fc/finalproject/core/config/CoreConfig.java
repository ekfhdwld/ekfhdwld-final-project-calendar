package com.jihye.fc.finalproject.core.config;

import com.jihye.fc.finalproject.core.util.BCryptEncryptor;
import com.jihye.fc.finalproject.core.util.Encryptor;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan("com.jihye.fc.finalproject.core")
@EnableJpaRepositories("com.jihye.fc.finalproject.core")
@EnableJpaAuditing
@Configuration
public class CoreConfig {
	
	@Bean
	public Encryptor bcryptEncryptor(){
		return new BCryptEncryptor();
	}
}

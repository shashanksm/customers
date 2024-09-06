package io.shashanksm.customers.configurations;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@Profile(value = {"default","test","local"})
@PropertySource("classpath:local-db-config.properties")
public class LocalDataConfiguration {
	
	@Value("${db.url}")
	private String dbUrl;
	
	@Value("${db.username}")
	private String username;
	
	@Value("${db.password}")
	private String password;
	
	@Bean
	private static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
	
	@Bean
	DataSource dataSource() {
		
		DriverManagerDataSource d = new DriverManagerDataSource(dbUrl, username, password);
		return d;
	}
	
}

package com.ktm;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.ktm.services.data.DataAccess;
import com.ktm.services.data.impl.DataAccessGdatastore;

// TODO: Auto-generated Javadoc
/**
 * The Class AppConfig.
 */
@Configuration
@EnableWebMvc
@ComponentScan
public class AppConfig extends WebMvcConfigurerAdapter {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
	 * #configureMessageConverters(java.util.List)
	 */
	@Override
	public void configureMessageConverters(
			List<HttpMessageConverter<?>> converters) {
		// configure jackson message converter for HTTP <-> JSON
		converters.add(new MappingJackson2HttpMessageConverter());
		super.configureMessageConverters(converters);
	}

	/**
	 * Sets the data access.
	 * 
	 * @return the data access
	 */
	@Bean
	public DataAccess setDataAccess() {
		// instantiate DataAccess interface with Google Datastore instance
		return new DataAccessGdatastore();
	}
}

package com.acme.customers.api.rest.v1;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.glassfish.jersey.jsonp.JsonProcessingFeature;

import com.acme.customers.api.rest.v1.filters.AuthFilter;
import com.acme.customers.api.rest.v1.filters.CorsFilter;
import com.acme.customers.api.rest.v1.filters.LoggerInterceptor;
import com.acme.customers.api.rest.v1.mappers.EmptyPayloadMapper;
import com.acme.customers.api.rest.v1.mappers.ResourceNotFoundMapper;
import com.acme.customers.api.rest.v1.resources.CustomerResource;

/**
 * Defining service context
 */
@ApplicationPath("/v1")
public class RestApplication extends Application {
	
	/**
	 * Overriding this method in order to tell the application to 
	 * consider Jackson as Json operations provider.
	 * 
	 * Once this method is override, it's needed to specify all classes
	 * that the application needs to consider when it startups, even  my resourcers.
	 * 
	 */
	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> classes = new HashSet<>();
		
		// ---------------------------------------------
		// When working using Jackson
		//classes.add(JacksonJsonProvider.class);
		//classes.add(JacksonProvider.class);
		// ---------------------------------------------
		
		// ---------------------------------------------
		// Working using JSON-P - When there's no specific need to work with Jackson, 
		// It enables a lightweight mode to work with JSON, and it's possible to use JAX-RS directly.
		classes.add(JsonProcessingFeature.class);
		// ---------------------------------------------
		
		classes.add(CustomerResource.class);
		
		classes.add(EmptyPayloadMapper.class);
		classes.add(ResourceNotFoundMapper.class);
		
		classes.add(AuthFilter.class);
		classes.add(CorsFilter.class);
		classes.add(LoggerInterceptor.class);
		
		return classes;
	}

}

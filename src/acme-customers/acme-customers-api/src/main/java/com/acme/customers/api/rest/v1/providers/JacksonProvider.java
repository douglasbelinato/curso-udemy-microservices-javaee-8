package com.acme.customers.api.rest.v1.providers;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * Configuration for Jackson  
 * JAX-RS enables input/output data configuration using @Provider annotation 
 */
@Provider
public class JacksonProvider implements ContextResolver<ObjectMapper> {
	
	public final ObjectMapper mapper;
	
	public JacksonProvider() {
		mapper = new ObjectMapper();
		
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
	}

	@Override
	public ObjectMapper getContext(Class<?> arg0) {
		return mapper;
	}

}

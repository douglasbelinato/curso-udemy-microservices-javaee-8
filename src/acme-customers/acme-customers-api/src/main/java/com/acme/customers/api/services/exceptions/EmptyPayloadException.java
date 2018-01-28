package com.acme.customers.api.services.exceptions;

public class EmptyPayloadException extends RuntimeException {

	private static final long serialVersionUID = -6653137588428780140L;
	
	private String resource;

    public EmptyPayloadException(String resource) {
        this.resource = resource;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }
}

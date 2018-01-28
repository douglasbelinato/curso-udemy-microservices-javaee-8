package com.acme.customers.api.services.exceptions;

public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -8065648060176943530L;
	
	private String resource;
    private String identifier;

    public ResourceNotFoundException(String resource, String identifier) {
        this.resource = resource;
        this.identifier = identifier;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }
}

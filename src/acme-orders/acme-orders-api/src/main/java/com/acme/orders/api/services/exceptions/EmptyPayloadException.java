package com.acme.orders.api.services.exceptions;

public class EmptyPayloadException extends RuntimeException {

	private static final long serialVersionUID = -7986201451070293405L;
	
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

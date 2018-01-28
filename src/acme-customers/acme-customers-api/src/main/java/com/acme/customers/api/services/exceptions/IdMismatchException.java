package com.acme.customers.api.services.exceptions;

public class IdMismatchException extends RuntimeException {

	private static final long serialVersionUID = 713253018739209431L;
	
	private String pathId;
    private String entityId;

    public IdMismatchException(String pathId, String entityId) {
        this.pathId = pathId;
        this.entityId = entityId;
    }

    public String getPathId() {
        return pathId;
    }

    public void setPathId(String pathId) {
        this.pathId = pathId;
    }

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }
}

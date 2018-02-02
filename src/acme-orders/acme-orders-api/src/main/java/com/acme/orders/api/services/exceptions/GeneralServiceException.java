package com.acme.orders.api.services.exceptions;

public class GeneralServiceException extends RuntimeException {

	private static final long serialVersionUID = 5704952399247090431L;

	public GeneralServiceException(Throwable cause) {
        super(cause);
    }
}

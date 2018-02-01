package com.acme.customers.api.services.exceptions;

/**
 * @author Tilen Faganel, Sunesis ltd.
 * @since 1.3.0
 */
public class GeneralServiceException extends RuntimeException {

	private static final long serialVersionUID = -7898394875495626385L;

	public GeneralServiceException(Throwable cause) {
        super(cause);
    }
}

package com.epam.irasov.filmlibrary.bd;

import org.apache.log4j.Logger;

public class ConnectionPoolException extends RuntimeException {
    private static Logger LOGGER = Logger.getLogger(ConnectionPoolException.class);

    public ConnectionPoolException() {
    }

    public ConnectionPoolException(String message) {
        super(message);
        LOGGER.info(message);
    }

    public ConnectionPoolException(String message, Throwable cause) {
        super(message, cause);
        LOGGER.info(message, cause);
    }

    public ConnectionPoolException(Throwable cause) {
        super(cause);
        LOGGER.info(cause);
    }
}

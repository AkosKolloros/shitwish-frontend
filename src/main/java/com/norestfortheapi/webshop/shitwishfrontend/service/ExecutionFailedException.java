package com.norestfortheapi.webshop.shitwishfrontend.service;

public class ExecutionFailedException extends RuntimeException {
    public ExecutionFailedException() {
        this("Could not Retrieve resource");
    }

    public ExecutionFailedException(String message) {
        super(message);
    }
}

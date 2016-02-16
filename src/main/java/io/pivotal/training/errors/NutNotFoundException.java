package io.pivotal.training.errors;

public class NutNotFoundException extends RuntimeException {

    public NutNotFoundException(String message) {
        super(message);
    }
}

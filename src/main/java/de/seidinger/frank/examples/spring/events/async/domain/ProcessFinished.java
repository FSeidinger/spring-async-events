package de.seidinger.frank.examples.spring.events.async.domain;

public class ProcessFinished {
    private final String id;
    private final String message;

    ProcessFinished(String id, String message) {
        this.id = id;
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }
}

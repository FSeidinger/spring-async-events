package de.seidinger.frank.examples.spring.events.async.domain;

public class StartProcess {
    private final String id;

    public StartProcess(final String id) {
        this.id = id;
    }

    String getId() {
        return id;
    }
}

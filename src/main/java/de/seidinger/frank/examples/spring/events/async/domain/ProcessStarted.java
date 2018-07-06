package de.seidinger.frank.examples.spring.events.async.domain;

class ProcessStarted {
    private final String id;

    ProcessStarted(final String id) {
        this.id = id;
    }

    String getId() {
        return id;
    }
}

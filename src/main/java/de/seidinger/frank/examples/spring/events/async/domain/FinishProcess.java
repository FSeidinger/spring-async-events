package de.seidinger.frank.examples.spring.events.async.domain;

class FinishProcess {
    private final String id;
    private final String message;

    @SuppressWarnings("SameParameterValue")
    FinishProcess(final String id, final String message) {
        this.id = id;
        this.message = message;
    }

    String getId() {
        return id;
    }

    String getMessage() {
        return message;
    }
}

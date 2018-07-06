package de.seidinger.frank.examples.spring.events.async.domain;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FinishProcess that = (FinishProcess) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, message);
    }
}

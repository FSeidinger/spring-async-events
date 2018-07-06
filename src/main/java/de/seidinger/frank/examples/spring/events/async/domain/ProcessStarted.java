package de.seidinger.frank.examples.spring.events.async.domain;

import java.util.Objects;

class ProcessStarted {
    private final String id;

    ProcessStarted(final String id) {
        this.id = id;
    }

    String getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProcessStarted that = (ProcessStarted) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}

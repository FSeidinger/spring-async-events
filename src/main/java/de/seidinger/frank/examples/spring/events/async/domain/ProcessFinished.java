package de.seidinger.frank.examples.spring.events.async.domain;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProcessFinished that = (ProcessFinished) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, message);
    }
}

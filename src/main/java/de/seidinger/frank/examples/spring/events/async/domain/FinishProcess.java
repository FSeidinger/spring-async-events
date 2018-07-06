/*
 * Copyright 2018 Frank Seidinger
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

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

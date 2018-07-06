package de.seidinger.frank.examples.spring.events.async.domain;

import mockit.Injectable;
import mockit.Tested;
import mockit.Verifications;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationEventPublisher;

class HelloWorldProcessTest {
    @Injectable
    private ApplicationEventPublisher publisher;

    @Tested
    private HelloWorldProcess process;

    @Test
    @DisplayName("When the StartProcess command is given, the process is started")
    void m1() throws Exception {
        final StartProcess command = new StartProcess(
                "1"
        );

        final ProcessStarted event = new ProcessStarted(
                command.getId()
        );

        process.handle(
                command
        );

        new Verifications() {{
           publisher.publishEvent(
                   event
           );
        }};
    }

    @Test
    @DisplayName("When the process is started, the FinishProcess command is given")
    void m2() {
        final ProcessStarted event = new ProcessStarted(
                "2"
        );

        final FinishProcess command = new FinishProcess(
                event.getId(),
                "Hello, world!"
        );


        process.when(
                event
        );

        new Verifications() {{
            publisher.publishEvent(
                    command
            );
        }};
    }

    @Test
    @DisplayName("When the FinishProcess command is given, the process is finished")
    void m3() throws Exception {
        final FinishProcess command = new FinishProcess(
                "1",
                "Got it"
        );

        final ProcessFinished event = new ProcessFinished(
                command.getId(),
                command.getMessage()
        );

        process.handle(
                command
        );

        new Verifications() {{
            publisher.publishEvent(
                    event
            );
        }};
    }
}
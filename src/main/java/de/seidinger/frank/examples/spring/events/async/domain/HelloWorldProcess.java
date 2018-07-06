package de.seidinger.frank.examples.spring.events.async.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class HelloWorldProcess {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final ApplicationEventPublisher eventPublisher;

    @Autowired
    public HelloWorldProcess(final ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @Async
    @EventListener
    public void handle(final StartProcess command) throws Exception {
        Thread.sleep(500);

        logger.info("Starting process");

        this.eventPublisher.publishEvent(
                new ProcessStarted(
                        command.getId()
                )
        );
    }

    @Async
    @EventListener
    public void when(final ProcessStarted event) {
        logger.info("Process started");

        this.eventPublisher.publishEvent(
                new FinishProcess(
                        event.getId(),
                        "Hello, world!"
                )
        );
    }

    @Async
    @EventListener
    public void handle(final FinishProcess command) throws Exception {
        Thread.sleep(300);

        logger.info("Finishing process");

        this.eventPublisher.publishEvent(
                new ProcessFinished(
                        command.getId(),
                        command.getMessage()
                )
        );
    }

    @Async
    @EventListener
    public void when(final ProcessFinished event) {
        logger.info("Process finished");
    }
}

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

package de.seidinger.frank.examples.spring.events.async.application;

import de.seidinger.frank.examples.spring.events.async.domain.ProcessFinished;
import de.seidinger.frank.examples.spring.events.async.domain.StartProcess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@RestController
class MainEndpoint {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final ExecutorService executorService;
    private final ApplicationEventPublisher eventPublisher;
    private final Map<String, CompletableFuture<String>> pendingResults;

    @Autowired
    public MainEndpoint(ApplicationEventPublisher eventPublisher) {
        this.executorService = Executors.newFixedThreadPool(5);
        this.eventPublisher = eventPublisher;
        pendingResults = new HashMap<>();
    }

    @RequestMapping("/")
    String helloWorld() throws Exception {
        return executeHelloWorldProcess();
    }

    private String executeHelloWorldProcess() throws Exception {
        final CompletableFuture<String> result = new CompletableFuture<>();

        this.executorService.submit(
                () -> {
                    final String processId = UUID.randomUUID().toString();
                    pendingResults.put(processId, result);

                    this.eventPublisher.publishEvent(
                            new StartProcess(
                                    processId
                            )
                    );
                }

        );

        return result.get(10, TimeUnit.SECONDS);
    }

    @Async
    @EventListener
    public void when(final ProcessFinished processFinished) {
        final CompletableFuture<String> result = pendingResults.get(processFinished.getId());

        if (result != null) {
            result.complete(processFinished.getMessage());
            pendingResults.remove(processFinished.getId());
        } else {
            logger.error("Could not find pending result for process {}", processFinished.getId());
        }
    }
}

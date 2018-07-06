# spring-async-events

This is an example project showing how to use a spring REST controller that
gets its result asynchronously from a background process.

You can find the REST controller in the
[MainEndpoint](https://github.com/FSeidinger/spring-async-events/blob/master/src/main/java/de/seidinger/frank/examples/spring/events/async/application/MainEndpoint.java)
class.

For the communication between the REST controller and the background process
the Java 8
[CompletableFuture](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/CompletableFuture.html)
and an
[ExecutorService](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ExecutorService.html) is used.

The background process is simply started by the
[StartProcess](https://github.com/FSeidinger/spring-async-events/blob/master/src/main/java/de/seidinger/frank/examples/spring/events/async/domain/StartProcess.java)
command

 The process is implemented by the
 [HelloWorldProcess](https://github.com/FSeidinger/spring-async-events/blob/master/src/main/java/de/seidinger/frank/examples/spring/events/async/domain/HelloWorldProcess.java)
 class
 
 The process goes through the phases:
 
 `StartProcess -> ProcessStarted -> FinishProcess -> ProcessFinished`
 
 Each phase is triggered by a command that will be handled by the registered
 handler and emits an event.
 
 When the `ProcessFinished` event is emitted, the process has come to its end.
 The content of this event delivers the result for the REST controller.
 
 

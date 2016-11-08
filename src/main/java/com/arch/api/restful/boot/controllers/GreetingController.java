package com.arch.api.restful.boot.controllers;

import com.arch.api.restful.boot.beans.Greeting;
import com.jcabi.aspects.Loggable;
import com.jcabi.aspects.RetryOnFailure;
import io.swagger.annotations.*;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;


@RestController
@RequestMapping("/wishes")
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();


    @Loggable(value=Loggable.ERROR)
    @RequestMapping(path = "/greeting" , method = RequestMethod.GET,consumes = MediaType.ALL_VALUE)
    @ApiOperation(value = "getGreeting", nickname = "getGreeting")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "User's name", required = false, dataType = "string", paramType = "query", defaultValue="Srini K")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Greeting.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {

        validateInfo(name);
      //  process(name);
        return new Greeting(counter.incrementAndGet(),
                String.format(template, name));
    }

    @Loggable(value = Loggable.WARN, name = "ValidationInfo method")
    private void validateInfo(String name) {
        System.out.println("Validation verify");
    }

    @RetryOnFailure(attempts = 2, delay = 100 ,types = ArithmeticException.class)
    public void process(String name) {

        System.out.println("Processing ...");
        int x = 10/0;

    }
}

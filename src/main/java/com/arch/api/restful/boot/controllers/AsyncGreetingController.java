package com.arch.api.restful.boot.controllers;

import com.arch.api.restful.boot.async.CustomAsyncBean;
import com.arch.api.restful.boot.beans.Greeting;
import io.swagger.annotations.*;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/wishes")
public class AsyncGreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();



    @RequestMapping(path = "/greetingAsync" , method = RequestMethod.GET,consumes = MediaType.ALL_VALUE)
    @ApiOperation(value = "greetingAsync", nickname = "greetingAsync")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "User's name", required = false, dataType = "string", paramType = "query", defaultValue="Srini K")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Greeting.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) throws InterruptedException {

       new CustomAsyncBean().timeConsumingMethod();
        return new Greeting(counter.incrementAndGet(),
                String.format(template, name));
    }

}

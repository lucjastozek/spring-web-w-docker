package com.neill.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {

    private final AtomicLong greetingCounter = new AtomicLong();


    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        //This will send back json containing an automatically converted version of this Greeting object.
        return new Greeting(greetingCounter.incrementAndGet(), String.format("Hello, %s!", name));
    }

    @GetMapping("/")
    public String potato() {
        return "Hi!  Try /greeting or /scores";
    }

}
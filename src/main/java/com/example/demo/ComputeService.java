package com.example.demo;

import org.springframework.web.bind.annotation.*;


@RestController
public class ComputeService
{
    @RequestMapping(value = "/greeting", method = RequestMethod.GET)
    public LoginResponse login()
    {
        return new LoginResponse("WORLD");
    }

//    @RequestMapping(value = "/login", method = RequestMethod.GET)
//    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name)
//    {
//        return new Greeting(counter.incrementAndGet(),
//            String.format(template, name));
//    }

}

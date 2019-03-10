package com.example.demo;

import org.springframework.web.bind.annotation.*;


@RestController
public class ComputeService
{
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public LoginResponse login()
    {
        return new LoginResponse("Some Data");
    }

}

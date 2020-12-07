package com.user.controller;

import com.demo.WrapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SomeController {

    @Autowired
    private WrapService someService;

    @GetMapping(value = "/wrap")
    public String wrapTest(String word) {
        return someService.wrap(word);
    }
}

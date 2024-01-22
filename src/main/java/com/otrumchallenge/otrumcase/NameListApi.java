package com.otrumchallenge.otrumcase;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/name-list")
public class NameListApi {

    @GetMapping("/{personId}")
    public String getNameListEl(String personId)
    {
        return "Name Found";
    }
}

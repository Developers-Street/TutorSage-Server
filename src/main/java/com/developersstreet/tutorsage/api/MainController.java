package com.developersstreet.tutorsage.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("")
@RequiredArgsConstructor
@Slf4j
public class MainController {
    @GetMapping("/")
    public String rootRoute(HttpServletRequest request, HttpServletRequest response) {
        return "THIS IS THE SERVER SIDE FOR TUTOR SAGE";
    }
}

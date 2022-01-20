package com.developersstreet.tutorsage.api;

import com.developersstreet.tutorsage.model.Class;
import com.developersstreet.tutorsage.service.ClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/class")
@RequiredArgsConstructor
public class ClassController {

    private final ClassService classService;

    @PostMapping("/create")
    public void createClass(@RequestBody Class c, HttpServletRequest request, HttpServletResponse response) {
        
    }
}

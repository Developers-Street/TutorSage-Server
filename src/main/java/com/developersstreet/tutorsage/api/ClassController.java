package com.developersstreet.tutorsage.api;

import com.developersstreet.tutorsage.model.Class;
import com.developersstreet.tutorsage.model.User;
import com.developersstreet.tutorsage.service.ClassService;
import com.developersstreet.tutorsage.service.UserService;
import com.developersstreet.tutorsage.service.UtilityService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequestMapping("/class")
@RequiredArgsConstructor
@Slf4j
public class ClassController {

    private final ClassService classService;
    private final UtilityService utilityService;
    private final UserService userService;

    @GetMapping("/")
    public void getClasses(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String query = request.getParameter("query");
            Long offset = Long.parseLong(request.getParameter("offset"));
            Long limit = Long.parseLong(request.getParameter("limit"));
            List<Class> classes = classService.getClassesByQueryAndOffsetAndLimit(query, offset, limit);
            new ObjectMapper().writeValue(response.getOutputStream(), classes);
        } catch (Exception exception) {
            utilityService.setExceptionResponse(exception, response);
        }
    }

    @GetMapping("/{id}")
    public void getOneClass(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Class c = classService.getClassById(id);
            if(c == null) throw new Exception("Class not found");
            new ObjectMapper().writeValue(response.getOutputStream(), c);
        } catch (Exception exception) {
            utilityService.setExceptionResponse(exception, response);
        }
    }

    @PostMapping("/create")
    public void createClass(@RequestBody Class c, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        try {
            User user = utilityService.getUserByAuthorizationHeader(authorizationHeader);
            c.setCreator(user);
            classService.createClass(c);
        } catch (Exception exception) {
            utilityService.setExceptionResponse(exception, response);
        }
    }

    @PostMapping("/join")
    public void joinClass(@RequestBody JoinClassForm classId, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        try {
            User user = utilityService.getUserByAuthorizationHeader(authorizationHeader);
            Class c = classService.addMemberToClass(classId.getClassId(), user);
        } catch (Exception exception) {
            utilityService.setExceptionResponse(exception, response);
        }
    }
}

@Data
class JoinClassForm {
    private Long classId;
}
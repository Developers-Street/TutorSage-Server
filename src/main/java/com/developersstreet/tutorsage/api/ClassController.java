package com.developersstreet.tutorsage.api;

import com.developersstreet.tutorsage.model.Class;
import com.developersstreet.tutorsage.model.User;
import com.developersstreet.tutorsage.service.ClassService;
import com.developersstreet.tutorsage.service.UtilityService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequestMapping("/class")
@RequiredArgsConstructor
@Slf4j
public class ClassController {

    private final ClassService classService;
    private final UtilityService utilityService;

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
            classService.addMemberToClass(classId.getClassId(), user);
        } catch (Exception exception) {
            utilityService.setExceptionResponse(exception, response);
        }
    }
}

@Data
class JoinClassForm {
    private Long classId;
}
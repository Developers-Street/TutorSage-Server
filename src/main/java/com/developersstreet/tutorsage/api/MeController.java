package com.developersstreet.tutorsage.api;

import com.developersstreet.tutorsage.model.User;
import com.developersstreet.tutorsage.model.UserData;
import com.developersstreet.tutorsage.service.UserService;
import com.developersstreet.tutorsage.service.UtilityService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequestMapping("/me")
@RequiredArgsConstructor
@Slf4j
public class MeController {

    private final UserService userService;
    private final UtilityService utilityService;

    @PostMapping("/data/save")
    public void saveDetails(@Valid @RequestBody UserData userData, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        try {
            User user = utilityService.getUserByAuthorizationHeader(authorizationHeader);
            userService.saveUserData(user, userData);
        } catch (Exception exception) {
            utilityService.setExceptionResponse(exception, response);
        }
    }

    @GetMapping("/")
    public void getUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        try {
            User user = utilityService.getUserByAuthorizationHeader(authorizationHeader);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            new ObjectMapper().writeValue(response.getOutputStream(), user);
        } catch (Exception exception) {
            utilityService.setExceptionResponse(exception, response);
        }
    }
}


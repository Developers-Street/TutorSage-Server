package com.developersstreet.tutorsage.api;

import com.developersstreet.tutorsage.model.User;
import com.developersstreet.tutorsage.service.UserService;
import com.developersstreet.tutorsage.service.UtilityService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UsersController {
    private final UserService userService;
    private final UtilityService utilityService;

    @GetMapping("/")
    public void getUsers(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String query = request.getParameter("query");
            Long offset = Long.parseLong(request.getParameter("offset"));
            Long limit = Long.parseLong(request.getParameter("limit"));
            List<User> users = userService.getUsers(query);
            if(users.size() == 0) throw new Exception("No Users found");
            new ObjectMapper().writeValue(response.getOutputStream(), users);
        } catch (Exception exception) {
            utilityService.setExceptionResponse(exception, response);
        }
    }

    @GetMapping("/{id}")
    public void getOneUser(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            User user = userService.getUserById(id);
            if(user == null) throw new Exception("User not found");
            new ObjectMapper().writeValue(response.getOutputStream(), user);
        } catch (Exception exception) {
            utilityService.setExceptionResponse(exception, response);
        }
    }
}

@Data
class RoleToUserForm {
    private String username;
    private String rolename;
}
package com.developersstreet.tutorsage.api;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.developersstreet.tutorsage.model.Organization;
import com.developersstreet.tutorsage.model.Role;
import com.developersstreet.tutorsage.model.User;
import com.developersstreet.tutorsage.service.OrganizationService;
import com.developersstreet.tutorsage.service.UserService;
import com.developersstreet.tutorsage.service.UtilityService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequestMapping("/organization")
@RequiredArgsConstructor
@Slf4j
public class OrganizationController {
    
    private final OrganizationService organizationService;
    private final UtilityService utilityService;
    private final UserService userService;

    @GetMapping("/")
    public void getOrganizations(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String query = request.getParameter("query");
            Long offset = Long.parseLong(request.getParameter("offset"));
            Long limit = Long.parseLong(request.getParameter("limit"));
            List<Organization> organizations = organizationService.getOrganizationsByQueryAndOffsetAndLimit(query, offset, limit);
            new ObjectMapper().writeValue(response.getOutputStream(), organizations);
        } catch (Exception exception) {
            utilityService.setExceptionResponse(exception, response);
        }
    }

    @GetMapping("/{id}")
    public void getOneOrganization(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Organization o = organizationService.getOrganizationById(id);
            if(o == null) throw new Exception("Organization not found");
            new ObjectMapper().writeValue(response.getOutputStream(), o);
        } catch(Exception exception) {
            utilityService.setExceptionResponse(exception, response);
        }
    }

    @PostMapping("/create")
    public void createOrganization(@RequestBody Organization o, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        try {
            User user = utilityService.getUserByAuthorizationHeader(authorizationHeader);
            o.setCreator(user);
            o.setAdmin(user);
            organizationService.createOrganization(o);
        } catch(Exception exception) {
            utilityService.setExceptionResponse(exception, response);
        }
    }
    
    @PostMapping("/join")
    public void joinOrganization(@RequestParam Long organizationId, @RequestParam Long roleId, HttpServletRequest request, HttpServletResponse response) throws IOException {
    	log.info("Request Recieved");
    	String authorizationHeader = request.getHeader(AUTHORIZATION);
    	try {
    		User user = utilityService.getUserByAuthorizationHeader(authorizationHeader);
    		Role role = userService.getRoleById(roleId);
    		organizationService.joinOrganization(organizationId, user, role);
    	} catch(Exception exception) {
    		utilityService.setExceptionResponse(exception, response);
    	}
    }
    
    @PostMapping("/student/join")
    public void joinOrganizationAsStudent(@RequestParam Long organizationId, HttpServletRequest request, HttpServletResponse response) throws IOException {
    	String authorizationHeader = request.getHeader(AUTHORIZATION);
    	log.info("Request recieved");
    	try {
    		User user = utilityService.getUserByAuthorizationHeader(authorizationHeader);
    		organizationService.joinOrganizationAsStudent(organizationId, user);
    	} catch(Exception exception) {
    		utilityService.setExceptionResponse(exception, response);
    	}
    }
}
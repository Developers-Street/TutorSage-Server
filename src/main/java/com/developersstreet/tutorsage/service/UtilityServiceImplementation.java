package com.developersstreet.tutorsage.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.developersstreet.tutorsage.model.user.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.FORBIDDEN;

@Service
@RequiredArgsConstructor
public class UtilityServiceImplementation implements UtilityService {

    private final UserService userService;

    @Override
    public HttpServletResponse setExceptionResponse(Exception exception, HttpServletResponse response) throws IOException {
        response.setHeader("error", exception.getMessage());
        response.setStatus(FORBIDDEN.value());
        Map<String, String> error = new HashMap<>();
        error.put("message", exception.getMessage());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), error);
        return response;
    }
    
    @Override
    public HttpServletResponse setMessageResponse(String message, HttpServletResponse response, Integer status) throws IOException {
    	response.setHeader("message", message);
    	response.setStatus(status);
    	Map<String, String> res = new HashMap<>();
    	res.put("message", message);
    	response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    	new ObjectMapper().writeValue(response.getOutputStream(), res);
    	return response;
    }

    @Override
    public User getUserByAuthorizationHeader(String authorizationHeader) throws Exception {
        if(authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new Exception("Token is missing");
        }
        String token = authorizationHeader.substring("Bearer ".length());
        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(token);
        String username = decodedJWT.getSubject();
        User user = userService.getUserByUsername(username);
        if(user == null) throw new Exception("User not found");
        return user;
    }
}

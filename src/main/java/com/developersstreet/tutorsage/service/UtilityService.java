package com.developersstreet.tutorsage.service;

import javax.servlet.http.HttpServletResponse;

import com.developersstreet.tutorsage.model.user.User;

import java.io.IOException;

public interface UtilityService {
    HttpServletResponse setExceptionResponse(Exception exception, HttpServletResponse response) throws IOException;
    User getUserByAuthorizationHeader(String authorizationHeader) throws Exception;
    HttpServletResponse setMessageResponse(String message, HttpServletResponse response, Integer status) throws IOException;
}

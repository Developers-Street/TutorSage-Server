package com.developersstreet.tutorsage.service;

import com.developersstreet.tutorsage.model.User;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface UtilityService {
    HttpServletResponse setExceptionResponse(Exception exception, HttpServletResponse response) throws IOException;
    User getUserByAuthorizationHeader(String authorizationHeader) throws Exception;
    HttpServletResponse setMessageResponse(String message, HttpServletResponse response, Integer status) throws IOException;
}

package com.developersstreet.tutorsage.service;

import com.developersstreet.tutorsage.model.User;

public interface UtilityService {
    User getUserByAuthorizationHeader(String authorizationHeader);
}

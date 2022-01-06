package com.developersstreet.tutorsage.model;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
public class UserMerged {
    @JsonUnwrapped
    private User user;

    @JsonUnwrapped
    private UserData userData;
}

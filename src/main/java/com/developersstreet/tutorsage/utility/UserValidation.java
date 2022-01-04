package com.developersstreet.tutorsage.utility;

import com.developersstreet.tutorsage.model.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidation {

    private static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    private static final Pattern VALID_USERNAME_REGEX = Pattern.compile("^[A-Za-z]\\w{3,29}$");
    private static final Pattern VALID_PASSWORD_REGEX = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,20}$");

    public UserValidation() {}

    public boolean validateEmail(String email) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        return matcher.find();
    }

    public boolean validateUsername(String username) {
        Matcher matcher = VALID_USERNAME_REGEX.matcher(username);
        return matcher.find();
    }

    public boolean validatePassword(String password) {
        Matcher matcher = VALID_PASSWORD_REGEX.matcher(password);
        return matcher.find();
    }

    public void validateUser(User user) throws Exception {
        if(!validateEmail(user.getEmail())) throw new Exception("Invalid email");
        if(!validateUsername(user.getUsername())) throw new Exception("Invalid username");
        if(!validatePassword(user.getPassword())) throw new Exception("Invalid password");
    }
}

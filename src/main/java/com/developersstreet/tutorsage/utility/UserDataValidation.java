package com.developersstreet.tutorsage.utility;

import com.developersstreet.tutorsage.model.UserData;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserDataValidation {

    private static final Pattern VALID_FIRST_NAME_REGEX = Pattern.compile("\\b([A-ZÀ-ÿ][-,a-z. ']+[ ]*)+");
    private static final Pattern VALID_MIDDLE_NAME_REGEX = Pattern.compile("\\b([A-ZÀ-ÿ][-,a-z. ']+[ ]*)+");
    private static final Pattern VALID_LAST_NAME_REGEX = Pattern.compile("\\b([A-ZÀ-ÿ][-,a-z. ']+[ ]*)+");
    private static final Pattern VALID_BIRTH_DAY_REGEX = Pattern.compile("^(0[1-9]|[12][0-9]|3[01])");
    private static final Pattern VALID_BIRTH_MONTH_REGEX = Pattern.compile("^(0[1-9]|1[012])");
    private static final Pattern VALID_BIRTH_YEAR_REGEX = Pattern.compile("(19[7-9][0-9]|20[0-1][0-9]|202[0-1])");
    private static final Pattern VALID_PHONE_NUMBER_REGEX = Pattern.compile("[6-9][0-9]{9}$");

    public boolean validateFirstname(String first_name) {
        Matcher matcher = VALID_FIRST_NAME_REGEX.matcher(first_name);
        return matcher.find();
    }

    public boolean validateMiddleName(String middle_name) {
        if(middle_name.length() == 0 || middle_name == null) return true;
        Matcher matcher = VALID_MIDDLE_NAME_REGEX.matcher(middle_name);
        return matcher.find();
    }

    public boolean validateLastName(String last_name) {
        Matcher matcher = VALID_LAST_NAME_REGEX.matcher(last_name);
        return matcher.find();
    }

    public boolean validateGender(String gender) {
        if(gender.equals("Male") || gender.equals("Female") || gender.equals("Other")) return true;
        return false;
    }

    public boolean validateBirthDay(String birth_day) {
        if(birth_day.length() != 2) return  false;
        Matcher matcher = VALID_BIRTH_DAY_REGEX.matcher(birth_day);
        return matcher.find();
    }

    public boolean validateBirthMonth(String birth_month) {
        if(birth_month.length() != 2) return  false;
        Matcher matcher = VALID_BIRTH_MONTH_REGEX.matcher(birth_month);
        return matcher.find();
    }

    public boolean validateBirthYear(String birth_year) {
        if(birth_year.length() != 4) return false;
        Matcher matcher = VALID_BIRTH_YEAR_REGEX.matcher(birth_year);
        return matcher.find();
    }

    public boolean validatePhoneNumber(String phone_number) {
        if(phone_number.length() != 10) return false;
        Matcher matcher = VALID_PHONE_NUMBER_REGEX.matcher(phone_number);
        return matcher.find();
    }

    public void validateUserData(UserData userData) throws Exception {
        if(!validateFirstname(userData.getFirst_name())) throw new Exception("Invalid first_name");
        if(!validateMiddleName(userData.getMiddle_name())) throw new Exception("Invalid middle_name");
        if(!validateLastName(userData.getLast_name())) throw new Exception("Invalid last_name");
        if(!validateBirthDay(userData.getBirth_day())) throw new Exception("Invalid birth_day");
        if(!validateBirthMonth(userData.getBirth_month())) throw new Exception("Invalid birth_month");
        if(!validateBirthYear(userData.getBirth_year())) throw new Exception("Invalid birth_year");
        if(!validateGender(userData.getGender())) throw new Exception("Invalid gender");
        if(!validatePhoneNumber(userData.getPhone_number())) throw new Exception("Invalid phone_number");
    }
}

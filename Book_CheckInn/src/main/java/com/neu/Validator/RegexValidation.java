package com.neu.Validator;

public class RegexValidation {
    //only number
    public boolean onlyNumbers(String s) {
        String regex = "^[0-9]+$";
        if (s.matches(regex)) {
            return true;
        }
        return false;
    }

    // only alphabets
    public boolean onlyAlphabets(String s) {
        String regex = "[a-zA-Z ]+";
        if (s.matches(regex)) {
            return true;
        }
        return false;
    }

        // email
    // only alphabets
    public boolean emailValidate(String s) {
        String regex = "[a-zA-Z_.0-9]+[\\w]+[a-zA-Z_0-9]+\\@[a-zA-Z]+\\.[a-zA-Z]+";
        if (s.matches(regex)) {
            return true;
        }
        return false;
    }

    public boolean phNo(String s) {
        String regex = "(\\+\\d{1,2}\\s)?\\(?\\d{3}\\)?[\\s.-]\\d{3}[\\s.-]\\d{4}$";
        if (s.matches(regex)) {
            return true;
        }
        return false;
    }

    public boolean alphaNumeric(String s) {
        String regex = "[a-zA-Z0-9]+";
        if (s.matches(regex)) {
            return true;
        }
        return false;
    }

    public boolean dateFormat(String d) {
        String regex = "^[0-3]?[0-9]/[0-3]?[0-9]/(?:[0-9]{2})?[0-9]{2}$";
        
        if (d.matches(regex)) {
            return true;
        }
        return false;
    }

}

package uutiset.wepauutiset.service;

import java.util.ArrayList;

public abstract class ValidationService {
    static boolean checkString(String toValidate, int minLenght, int maxLength) {
            if (toValidate.length() >= minLenght) {
            return toValidate.length() <= maxLength;
        }
        return false;

    }

    public static void validateGiven(String prefix, String validatable, ArrayList<String> errors, int min, int max) {
        if (!checkString(validatable, min, max)) {
            errors.add(prefix + " pituus tulee olla " + min + " ja " + max + " merkin välillä.");
        }
    }

}


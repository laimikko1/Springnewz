package uutiset.wepauutiset.domain;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NewsValidatorService {


    public List<String> validateNews(News news) {
        ArrayList<String> errors = new ArrayList<>();
        validateGiven("Sisällön", news.getContent(), errors, 50, 1000);
        validateGiven("Otsikon", news.getHeader(), errors, 5, 50);
        validateGiven("Ingressin", news.getIngress(), errors, 10, 255);
        checkWritersAndCategory(news, errors);

        return errors;
    }

    public static boolean checkString(String toValidate, int minLenght, int maxLength) {
        if(toValidate.length() >= minLenght) {
            return toValidate.length() <= maxLength;
        }
        return false;

    }

    public static void validateGiven(String prefix, String validatable, ArrayList<String> errors, int min, int max) {
        if (!checkString(validatable, 5, 30)) {
            errors.add(prefix + " pituus tulee olla " + min + " ja " + max + " merkin välillä.");
        }
    }

    public static void checkWritersAndCategory(News news, ArrayList<String> errors) {
        if (news.getWriters() == null) {
            errors.add("Uutisella tulee olla ainakin yksi kirjoittaja.");
        }
        if (news.getCategories() == null) {
            errors.add("Uutisella tulee olla ainakin yksi kategoria.");
        }

    }


}

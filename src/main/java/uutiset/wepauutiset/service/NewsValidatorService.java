package uutiset.wepauutiset.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uutiset.wepauutiset.domain.News;

import java.util.ArrayList;
import java.util.List;

@Service
public class NewsValidatorService extends ValidationService {


    public List<String> validateNews(News news) {
        ArrayList<String> errors = new ArrayList<>();
        validateGiven("Sisällön", news.getContent(), errors, 50, 1000);
        validateGiven("Otsikon", news.getHeader(), errors, 5, 50);
        validateGiven("Ingressin", news.getIngress(), errors, 10, 255);
        checkWritersAndCategory(news, errors);
        return errors;
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

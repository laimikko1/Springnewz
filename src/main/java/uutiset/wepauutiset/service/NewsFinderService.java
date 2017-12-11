package uutiset.wepauutiset.service;

import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import uutiset.wepauutiset.domain.News;
import uutiset.wepauutiset.domain.NewsClick;
import uutiset.wepauutiset.repository.NewsClickRepository;
import uutiset.wepauutiset.repository.NewsRepository;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class NewsFinderService {

    private Pageable pageable;

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private NewsClickRepository newsClickRepository;


    public Page findNewest() {
        this.pageable = PageRequest.of(0, 5, Sort.Direction.DESC, "publishdate");
        return newsRepository.findAll(pageable);
    }

    @Transactional
    public List<News> findMostPopular(List<News> n) {


        List<News> lastWeekPopular = new ArrayList<>();

        for (News ne : n) {
            ne.setClicks(listOnlyLastWeekClicks(ne));
            lastWeekPopular.add(ne);
        }

        lastWeekPopular = sortInServiceCauseNewsCompareToNotWorking(lastWeekPopular);

        if (lastWeekPopular.size() < 5) {
            return lastWeekPopular;
        }
        return lastWeekPopular.subList(0, 5);


    }


    public List<NewsClick> listOnlyLastWeekClicks(News n) {
        Collections.sort(n.getClicks());
        List<NewsClick> lastweek = new ArrayList<>();
        for (NewsClick newsclick : n.getClicks()) {
            if (newsclick.getClickdate().isAfter(LocalDate.now().minusDays(7))) {
                lastweek.add(newsclick);
            } else {
                break;
            }
        }
        return lastweek;
    }

    public List<News> findAllByCategory(Long id) {
        return newsRepository.findByCategory(id);
    }

    // Ääärimmilleen optimoitu BubbleSort käyttöön (copypasta tietenkin) koska tuo compareTo ei vain halua jutella näiden
    // repositorioista haettujen objektien kanssa, jostain mystisestä syystä toimii vain viimeisenä
    // luodulla uutisella, muuten pukkaa nullPointteria ja ei suostu näyttämään sivun listausta.
    // Tässä luokan sisällä löytyy kaikki objektit, joten tehdään nyt näin
    private List<News> sortInServiceCauseNewsCompareToNotWorking(List<News> list) {
        int n = list.size();
        int k;
        for (int m = n; m >= 0; m--) {
            for (int i = 0; i < n - 1; i++) {
                k = i + 1;
                if (list.get(i).getClicks().size() < list.get(k).getClicks().size()) {
                    News vali = list.get(i);
                    list.set(i, list.get(k));
                    list.set(k, vali);
                }
            }
        }
        return list;

    }
}





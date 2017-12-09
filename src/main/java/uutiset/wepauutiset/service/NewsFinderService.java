package uutiset.wepauutiset.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import uutiset.wepauutiset.domain.News;
import uutiset.wepauutiset.domain.NewsClick;
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


    public Page findNewest() {
        this.pageable = PageRequest.of(0, 5, Sort.Direction.DESC, "publishdate");
        return newsRepository.findAll(pageable);
    }

    public List<News> findMostPopular() {
        List<News> n = newsRepository.findAll();
        List<News> lastWeekPopular = new ArrayList<>();

        for (News ne : n) {
            ne.setClicks(listOnlyLastWeekClicks(ne));
            lastWeekPopular.add(ne);
        }

        Collections.sort(lastWeekPopular);
        if (lastWeekPopular.size() < 5) {
            return lastWeekPopular;
        }
        return lastWeekPopular.subList(0, 5);


    }

    public List<NewsClick> listOnlyLastWeekClicks(News n) {
        Collections.sort(n.getClicks());
        List<NewsClick> lastweek = new ArrayList<>();
        for (NewsClick newsclick : n.getClicks()) {
            if (newsclick.getClickdate().isBefore(LocalDate.now().minusDays(7))) {
                lastweek.add(newsclick);
            }
            break;
        }
        return lastweek;
    }

    public List<News> findAllByCategory(Long id) {
        return newsRepository.findByCategory(id);
    }

}

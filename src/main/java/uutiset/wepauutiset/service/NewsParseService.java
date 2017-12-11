package uutiset.wepauutiset.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import uutiset.wepauutiset.domain.News;
import uutiset.wepauutiset.domain.NewsClick;
import uutiset.wepauutiset.domain.NewsObject;
import uutiset.wepauutiset.repository.NewsClickRepository;
import uutiset.wepauutiset.repository.NewsObjectRepository;
import uutiset.wepauutiset.repository.NewsRepository;

import java.time.LocalDate;
import java.util.ArrayList;

@Service
public class NewsParseService {


    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private NewsClickRepository newsClickRepository;

    @Autowired
    private NewsObjectRepository newsObjectRepository;


    public News parseEditable(Long id) {
        News news = newsRepository.getOne(id);
        NewsClick nc = new NewsClick();
        nc.setNews(news);
        nc.setClickdate(LocalDate.now());

        newsClickRepository.save(nc);
        news.addLick(nc);
        newsRepository.save(news);
        return news;

    }

    public void parseModelAttributeDataToNews(Long id, News news) {
        News n = newsRepository.getOne(id);

        n.setContent(news.getContent());
        n.setIngress(news.getIngress());
        n.setHeader(news.getHeader());
        n.setWriters(news.getWriters());
        n.setCategories(news.getCategories());
        newsRepository.save(n);


    }

    public void parseNonFormDataToNewNewsstory(News news) {
        news.setClicks(new ArrayList<>());
        news.setPublishdate(LocalDate.now());

        newsRepository.save(news);
    }
}

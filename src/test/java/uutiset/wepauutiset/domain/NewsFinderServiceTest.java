package uutiset.wepauutiset.domain;


import org.apache.tomcat.jni.Local;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import uutiset.wepauutiset.repository.CategoryRepository;
import uutiset.wepauutiset.repository.NewsRepository;
import uutiset.wepauutiset.service.NewsFinderService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NewsFinderServiceTest {

    private News first;
    private News second;
    private NewsFinderService newsFinderService;

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Before
    public void setUp() {
        this.first = new News();
        this.second = new News();
        this.first.setClicks(new ArrayList<>());
        this.second.setClicks(new ArrayList<>());
        this.newsFinderService = new NewsFinderService();
    }

    @Test
    public void lastWeekNewsOnlyReturnsLastWeek() {
        setUpNewsClickList();


        List<NewsClick> lastWeek = newsFinderService.listOnlyLastWeekClicks(first);

        assertEquals(2, lastWeek.size());
    }


    @Test
    public void findMostPopularListMostPopular() {
        setUpNewsClickList();
        setUpNewsClickList();
        this.second.addLick(new NewsClick(second, LocalDate.now().minusDays(8)));
        this.second.addLick(new NewsClick(second, LocalDate.now().minusDays(8)));
        this.second.addLick(new NewsClick(second, LocalDate.now().minusDays(8)));
        this.second.addLick(new NewsClick(second, LocalDate.now().minusDays(7)));
        this.second.addLick(new NewsClick(second, LocalDate.now().minusDays(7)));
        this.second.addLick(new NewsClick(second, LocalDate.now().minusDays(3)));

        List<News> n = new ArrayList<>();
        n.add(first);
        n.add(second);
//        n = newsFinderService.findMostPopular(n);

        assertEquals(2, n.size());
        assertEquals(n.get(0).getClicks().size(), 4);
        assertEquals(n.get(1).getClicks().size(), 1);
    }

    private void setUpNewsClickList() {
        this.first.addLick(new NewsClick(first, LocalDate.now()));
        this.first.addLick(new NewsClick(first, LocalDate.now()));
        this.first.addLick(new NewsClick(first, LocalDate.now().minusDays(8)));
    }


}

package uutiset.wepauutiset.domain;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NewsTest {

    private News n;

    @Before
    public void setUp() {
        this.n = new News();
        n.setClicks(new ArrayList());
        NewsClick nc = new NewsClick();
        n.addLick(nc);
        n.addLick(new NewsClick());
    }


    @Test
    public void compareToReturnsLarger() {
        News na = new News();
        na.setClicks(new ArrayList<>());
        na.addLick(new NewsClick());

        List<News> news = new ArrayList<>();
        news.add(na);
        news.add(n);

        Collections.sort(news);
        assertEquals(news.get(0), n);

    }

}

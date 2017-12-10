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

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NewsClickTest {

    private NewsClick n;

    @Before
    public void setUp() {
        this.n = new NewsClick();
        n.setClickdate(LocalDate.now());
    }


    @Test
    public void compareToReturnsLarger() {
        List<NewsClick> list = new ArrayList<>();
        NewsClick another = new NewsClick();
        another.setClickdate(LocalDate.now().minusDays(5));
        list.add(n);
        list.add(another);
        Collections.sort(list);

        assertEquals(list.get(0), n);
    }
}

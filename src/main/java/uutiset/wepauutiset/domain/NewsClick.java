package uutiset.wepauutiset.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class NewsClick extends AbstractPersistable<Long> implements Comparable<NewsClick>{

    @ManyToOne
    private News news;

    private LocalDate clickdate;



    @Override
    public int compareTo(NewsClick newsClick) {
        return this.clickdate.compareTo(newsClick.clickdate);
    }
}


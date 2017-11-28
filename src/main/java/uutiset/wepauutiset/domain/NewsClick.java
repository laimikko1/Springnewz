package uutiset.wepauutiset.domain;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class NewsClick extends AbstractPersistable<Long>{

    private LocalDate clickDate;

    @JoinColumn
    private News news;
}

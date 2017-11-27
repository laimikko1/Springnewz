package uutiset.wepauutiset.domain;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class NewsClick {

    private LocalDate clickDate;

    @JoinColumn
    private News news;
}

package uutiset.wepauutiset.domain;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Component
@Entity
public class News extends AbstractPersistable<Long> {

    @Min(10)
    private String header;
    @Min(10)
    private String ingress;
    @Min(20)
    private String text;
    private LocalDate created;
//    private List<Newswriter> writers;
}

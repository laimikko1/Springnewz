package uutiset.wepauutiset.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class News extends AbstractPersistable<Long> {

    @Size(min = 10)
    private String header;

    @Size(min = 10)
    private String ingress;

    @Size(min = 20)
    private String content;

    private LocalDate publishdate;

    @OneToMany
    private List<NewsWriter> writers;

    @OneToMany
    private List<Category> categories;

    @OneToOne
    @JoinColumn
    private NewsObject newsObject;

}

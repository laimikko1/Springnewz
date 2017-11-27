package uutiset.wepauutiset.domain;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
public class News extends AbstractPersistable<Long> {

    @Size(min=10)
    private String header;

    @Size(min=10)
    private String ingress;

    @Size(min=20)
    private String content;

    private LocalDate publishdate;

    @OneToMany
    private List<Newswriter> writers;

    @OneToMany
    private List<Category> categories;
}

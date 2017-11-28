package uutiset.wepauutiset.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class NewsWriter extends AbstractPersistable<Long> {

    @Size(min=3, max=20)
    private String name;

    @OneToMany(mappedBy = "writers")
    private List<News> writtenNews;

}

package uutiset.wepauutiset.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Newswriter extends AbstractPersistable<Long> {

    private String name;
    private String password;

    @ManyToMany(mappedBy = "writers")
    private List<News> writtenNews;

}

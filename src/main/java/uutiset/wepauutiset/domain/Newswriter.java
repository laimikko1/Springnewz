package uutiset.wepauutiset.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Newswriter {

    @Size(min=3, max=20)
    private String name;

    @OneToMany(mappedBy = "writers")
    private List<News> writtenNews;

}

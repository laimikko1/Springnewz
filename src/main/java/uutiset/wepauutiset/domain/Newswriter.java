package uutiset.wepauutiset.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Valid
public class Newswriter extends AbstractPersistable<Long> {

    @Size(min=3, max=15, message = "Writer name must be between 3 and 15 characters!")
    @Pattern(regexp = "^[a-zA-Z_ ]*$", message = "Name must contain only letters!")
    private String name;

    @ManyToMany(mappedBy = "writers")
    private List<News> writtenNews;

}

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

    @NotEmpty(message = "Name must not be empty!")
    @Size(min=3, max=10, message = "Name must be at least 3 characters!")
    @Pattern(regexp = "^[A-Za-z]+$", message = "Name must contain only letters!")
    private String name;
    @NotEmpty(message = "Password must not be empty!")
    @Size(min=5, message = "Password must contain at least 5 characters!")
    private String password;

    @ManyToMany(mappedBy = "writers")
    private List<News> writtenNews;

}

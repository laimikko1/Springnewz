package uutiset.wepauutiset.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Valid

public class Category extends AbstractPersistable<Long> {

    @Size(min = 3, max = 15, message = "Category name must be between 3 and 15 chacters!")
    private String name;

    @NotNull
    private boolean pinned;

    @ManyToMany(mappedBy = "categories")
    private List<News> categoryNews;


}

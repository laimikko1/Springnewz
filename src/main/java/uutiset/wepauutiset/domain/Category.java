package uutiset.wepauutiset.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data

public class Category extends AbstractPersistable<Long>{

    private String name;

    private boolean isPinnedToMenu;

    @OneToMany(mappedBy = "categories")
    private List<News> categoryNews;

}

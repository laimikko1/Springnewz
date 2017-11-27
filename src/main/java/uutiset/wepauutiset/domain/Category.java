package uutiset.wepauutiset.domain;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor

public class Category {

    private String categoryName;

    private boolean isPinnedToMenu;

    @OneToMany(mappedBy = "categories")
    private List<News> categoryNews;

}

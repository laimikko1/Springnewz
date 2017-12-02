package uutiset.wepauutiset.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class News extends AbstractPersistable<Long> {

    private String header;

    private String ingress;

    private String content;

    private LocalDate publishdate;

    @ManyToMany
    private List<NewsWriter> writers;

    @ManyToMany
    private List<Category> categories;

    @OneToOne
    private NewsObject newsObject;

    public void addNewsWriter(NewsWriter writer) {
        if (this.writers == null) {
            this.writers = new ArrayList<>();
        }

        this.writers.add(writer);
    }

    public void addCategory(Category category) {
        if (this.categories == null) {
            this.categories = new ArrayList<>();
        }

        this.categories.add(category);
    }


}

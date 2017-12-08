package uutiset.wepauutiset.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
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
    @Size(min = 50, max = 1000)
    private String content;

    private Integer clicks;

    private LocalDate publishdate;

    @ManyToMany
    private List<Newswriter> writers;

    @ManyToMany
    private List<Category> categories;

    @OneToOne
    private NewsObject newsObject;

    public void addNewsWriter(Newswriter writer) {
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

    public void addclick() {
        this.clicks++;
    }


}

package uutiset.wepauutiset.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Valid
public class News extends AbstractPersistable<Long> implements Comparable<News> {

    @Size(min = 5, max = 20, message = "Header must be between 5 and 20 characters!")
    private String header;

    @Size(min = 10, max = 250, message = "Ingress must be between 10 and 250 characters!")
    private String ingress;

    @Size(min = 50, max = 1000, message = "Content must be between 50 and 1000 characters!")
    private String content;

    @OneToMany
    private List<NewsClick> clicks;

    private LocalDate publishdate;

    private Long editId;

    @NotEmpty(message = "Select at least one writer!")
    @ManyToMany
    private List<Newswriter> writers;

    @NotEmpty(message = "Select at least one category!")
    @ManyToMany
    private List<Category> categories;

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


    public void addLick(NewsClick newsClick) {
        this.clicks.add(newsClick);
    }

    @Override
    public int compareTo(News news) {
        return news.clicks.size() - this.clicks.size();
    }
}

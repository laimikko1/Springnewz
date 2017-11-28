package uutiset.wepauutiset.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class NewsObject extends AbstractPersistable<Long>{


    @Lob
    private byte[] content;

    @OneToOne(mappedBy = "newsObject")
    private News news;

}

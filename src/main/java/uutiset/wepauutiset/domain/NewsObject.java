package uutiset.wepauutiset.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class NewsObject extends AbstractPersistable<Long> {

    @Lob
    private byte[] content;

    @OneToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private News news;



}

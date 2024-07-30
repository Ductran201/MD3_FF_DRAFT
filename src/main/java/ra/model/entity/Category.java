package ra.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String url;
    @Column(columnDefinition = "bit default true")
    private Boolean status;
    @Temporal(TemporalType.DATE)
    private Date createdDate;
}

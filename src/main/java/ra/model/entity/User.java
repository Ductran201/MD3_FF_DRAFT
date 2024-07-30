package ra.model.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    private String fullName;
    private String address;
    private String avatar;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createdDate;
    private Boolean status;
    private Boolean gender;
    @ManyToMany
    @JoinTable(name = "user_role", // can miss fetch
            joinColumns = @JoinColumn(name = "user_id"), // join to the name of field in database not in class
            inverseJoinColumns = @JoinColumn(name = "role_id") // join to other manyToMany relation
    )
    private Set<Role> roleSet;
}

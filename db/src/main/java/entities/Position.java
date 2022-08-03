package entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "position")
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_position;

    private String positionName;

    @OneToMany(mappedBy = "position")
    private Set<Engineer> engineers;
}

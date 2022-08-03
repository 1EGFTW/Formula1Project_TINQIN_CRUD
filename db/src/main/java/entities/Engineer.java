package entities;

import javax.persistence.*;

@Entity
@Table(name = "engineer")
public class Engineer {
    @Id
    @GeneratedValue
    private Long id_engeneer;

    private String firstName;

    private String lastName;

    @ManyToOne
    private Team team;

    @ManyToOne
    private Position position;
}

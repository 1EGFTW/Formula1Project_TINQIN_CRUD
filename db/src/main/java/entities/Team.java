package entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "team")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_team;

    private String teamName;

    private Integer numberOfChampionships;

    private Integer constructorPoints;

    @OneToMany(mappedBy = "team")
    private Set<Car> cars;

    @OneToMany(mappedBy = "team")
    private Set<Engineer> engineers;

    @OneToMany(mappedBy = "team")
    private Set<Driver> drivers;
}

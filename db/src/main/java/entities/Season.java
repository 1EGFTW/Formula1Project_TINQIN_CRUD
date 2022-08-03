package entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "season")
public class Season {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_season;

    private Integer year;

    @ManyToOne
    private Driver champion;

    @OneToMany(mappedBy = "season")
    private Set<RaceSeason> raceSeasons;
}

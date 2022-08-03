package entities;

import javax.persistence.*;

@Entity
@Table(name = "race_season")
public class RaceSeason {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_race_season;

    @ManyToOne
    private Race race;

    @ManyToOne
    private Season season;
}

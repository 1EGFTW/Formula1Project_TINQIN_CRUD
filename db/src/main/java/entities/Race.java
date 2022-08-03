package entities;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "race")
public class Race {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_race;

    private String circuitName;

    private Integer year;

    private Boolean isCompleted;

    @ManyToOne
    private Driver winner;

    @Temporal(TemporalType.DATE)
    private Date date;

    private Integer numberOfLaps;

    private Double distancePerLap;

    private Double latitude;

    private Double longitude;

    @OneToMany(mappedBy = "race")
    private Set<RaceSeason> raceSeasons;
}

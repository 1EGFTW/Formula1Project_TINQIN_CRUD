package entities;

import javax.persistence.*;
import java.util.Objects;
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

    public Season() {
    }

    public Season(Long id_season, Integer year, Driver champion, Set<RaceSeason> raceSeasons) {
        this.id_season = id_season;
        this.year = year;
        this.champion = champion;
        this.raceSeasons = raceSeasons;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Season season = (Season) o;
        return Objects.equals(id_season, season.id_season) && Objects.equals(year, season.year) && Objects.equals(champion, season.champion) && Objects.equals(raceSeasons, season.raceSeasons);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_season, year, champion, raceSeasons);
    }

    public Long getId_season() {
        return id_season;
    }

    public void setId_season(Long id_season) {
        this.id_season = id_season;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Driver getChampion() {
        return champion;
    }

    public void setChampion(Driver champion) {
        this.champion = champion;
    }

    public Set<RaceSeason> getRaceSeasons() {
        return raceSeasons;
    }

    public void setRaceSeasons(Set<RaceSeason> raceSeasons) {
        this.raceSeasons = raceSeasons;
    }
}

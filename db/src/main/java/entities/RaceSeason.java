package entities;

import javax.persistence.*;
import java.util.Objects;

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

    public RaceSeason() {
    }

    public RaceSeason(Long id_race_season, Race race, Season season) {
        this.id_race_season = id_race_season;
        this.race = race;
        this.season = season;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RaceSeason that = (RaceSeason) o;
        return Objects.equals(id_race_season, that.id_race_season) && Objects.equals(race, that.race) && Objects.equals(season, that.season);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_race_season, race, season);
    }

    public Long getId_race_season() {
        return id_race_season;
    }

    public void setId_race_season(Long id_race_season) {
        this.id_race_season = id_race_season;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }
}

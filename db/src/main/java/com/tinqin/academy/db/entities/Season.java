package com.tinqin.academy.db.entities;

import lombok.Builder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
@Builder
@Entity
@Table(name = "season")
public class Season {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_season;

    private Integer year;

    @ManyToOne
    @JoinColumn(name = "id_driver")
    private Driver champion;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "race_season",
            joinColumns = @JoinColumn(name = "id_season"),
            inverseJoinColumns = @JoinColumn(name = "id_race")
    )
    private List<Race> races=new ArrayList<>();

    public Season() {
    }

    public Season(Long id_season, Integer year, Driver champion, List<Race> races) {
        this.id_season = id_season;
        this.year = year;
        this.champion = champion;
        this.races = races;
    }

    public List<Race> getRaces() {
        return races;
    }

    public void setRaces(List<Race> races) {
        this.races = races;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Season season = (Season) o;
        return Objects.equals(id_season, season.id_season) && Objects.equals(year, season.year) && Objects.equals(champion, season.champion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_season, year, champion);
    }

}

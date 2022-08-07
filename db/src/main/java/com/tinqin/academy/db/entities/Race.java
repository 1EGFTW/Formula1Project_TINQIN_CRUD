package com.tinqin.academy.db.entities;

import lombok.Builder;

import javax.persistence.*;
import java.util.*;
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
    @JoinColumn(name = "id_driver")
    private Driver winner;

    @Temporal(TemporalType.DATE)
    private Date date;

    private Integer numberOfLaps;

    private Double distancePerLap;

    private Double latitude;

    private Double longitude;

    @ManyToMany(mappedBy = "races")
    private List<Season> seasons = new ArrayList<>();

    public Race() {
    }

    public Race(Long id_race, String circuitName, Integer year, Boolean isCompleted, Driver winner, Date date, Integer numberOfLaps, Double distancePerLap, Double latitude, Double longitude, List<Season> seasons) {
        this.id_race = id_race;
        this.circuitName = circuitName;
        this.year = year;
        this.isCompleted = isCompleted;
        this.winner = winner;
        this.date = date;
        this.numberOfLaps = numberOfLaps;
        this.distancePerLap = distancePerLap;
        this.latitude = latitude;
        this.longitude = longitude;
        this.seasons = seasons;
    }

    public Race(String circuitName, Integer year, Boolean isCompleted, Driver winner, Date date, Integer numberOfLaps, Double distancePerLap, Double latitude, Double longitude) {
        this.circuitName = circuitName;
        this.year = year;
        this.isCompleted = isCompleted;
        this.winner = winner;
        this.date = date;
        this.numberOfLaps = numberOfLaps;
        this.distancePerLap = distancePerLap;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public List<Season> getSeasons() {
        return seasons;
    }

    public void setSeasons(List<Season> seasons) {
        this.seasons = seasons;
    }

    public Long getId_race() {
        return id_race;
    }

    public void setId_race(Long id_race) {
        this.id_race = id_race;
    }

    public String getCircuitName() {
        return circuitName;
    }

    public void setCircuitName(String circuitName) {
        this.circuitName = circuitName;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Boolean getCompleted() {
        return isCompleted;
    }

    public void setCompleted(Boolean completed) {
        isCompleted = completed;
    }

    public Driver getWinner() {
        return winner;
    }

    public void setWinner(Driver winner) {
        this.winner = winner;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getNumberOfLaps() {
        return numberOfLaps;
    }

    public void setNumberOfLaps(Integer numberOfLaps) {
        this.numberOfLaps = numberOfLaps;
    }

    public Double getDistancePerLap() {
        return distancePerLap;
    }

    public void setDistancePerLap(Double distancePerLap) {
        this.distancePerLap = distancePerLap;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Race race = (Race) o;
        return Objects.equals(id_race, race.id_race) && Objects.equals(circuitName, race.circuitName) && Objects.equals(year, race.year) && Objects.equals(isCompleted, race.isCompleted) && Objects.equals(winner, race.winner) && Objects.equals(date, race.date) && Objects.equals(numberOfLaps, race.numberOfLaps) && Objects.equals(distancePerLap, race.distancePerLap) && Objects.equals(latitude, race.latitude) && Objects.equals(longitude, race.longitude);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_race, circuitName, year, isCompleted, winner, date, numberOfLaps, distancePerLap, latitude, longitude);
    }
}

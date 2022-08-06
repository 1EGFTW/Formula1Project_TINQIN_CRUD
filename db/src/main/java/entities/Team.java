package entities;

import lombok.Builder;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "team")
@Builder
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

    public Team() {
    }

    public Team(Long id_team, String teamName, Integer numberOfChampionships, Integer constructorPoints, Set<Car> cars, Set<Engineer> engineers, Set<Driver> drivers) {
        this.id_team = id_team;
        this.teamName = teamName;
        this.numberOfChampionships = numberOfChampionships;
        this.constructorPoints = constructorPoints;
        this.cars = cars;
        this.engineers = engineers;
        this.drivers = drivers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return Objects.equals(id_team, team.id_team) && Objects.equals(teamName, team.teamName) && Objects.equals(numberOfChampionships, team.numberOfChampionships) && Objects.equals(constructorPoints, team.constructorPoints) && Objects.equals(cars, team.cars) && Objects.equals(engineers, team.engineers) && Objects.equals(drivers, team.drivers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_team, teamName, numberOfChampionships, constructorPoints, cars, engineers, drivers);
    }

    public Long getId_team() {
        return id_team;
    }

    public void setId_team(Long id_team) {
        this.id_team = id_team;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Integer getNumberOfChampionships() {
        return numberOfChampionships;
    }

    public void setNumberOfChampionships(Integer numberOfChampionships) {
        this.numberOfChampionships = numberOfChampionships;
    }

    public Integer getConstructorPoints() {
        return constructorPoints;
    }

    public void setConstructorPoints(Integer constructorPoints) {
        this.constructorPoints = constructorPoints;
    }

    public Set<Car> getCars() {
        return cars;
    }

    public void setCars(Set<Car> cars) {
        this.cars = cars;
    }

    public Set<Engineer> getEngineers() {
        return engineers;
    }

    public void setEngineers(Set<Engineer> engineers) {
        this.engineers = engineers;
    }

    public Set<Driver> getDrivers() {
        return drivers;
    }

    public void setDrivers(Set<Driver> drivers) {
        this.drivers = drivers;
    }
}

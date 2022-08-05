package entities;

import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_car;

    private String modelName;

    private Integer horsepower;

    private Integer torque;

    @ManyToOne
    @JoinColumn(name = "id_team")
    private Team team;

    @OneToMany(mappedBy = "car")
    private Set<Driver> drivers;

    @OneToMany(mappedBy = "winner")
    private Set<Race> races;

    public Car() {
    }

    public Car(String modelName, Integer horsepower, Integer torque, Team team, Set<Driver> drivers, Set<Race> races) {
        this.modelName = modelName;
        this.horsepower = horsepower;
        this.torque = torque;
        this.team = team;
        this.drivers = drivers;
        this.races = races;
    }

    public Long getId_car() {
        return id_car;
    }

    public void setId_car(Long id_car) {
        this.id_car = id_car;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public Integer getHorsepower() {
        return horsepower;
    }

    public void setHorsepower(Integer horsepower) {
        this.horsepower = horsepower;
    }

    public Integer getTorque() {
        return torque;
    }

    public void setTorque(Integer torque) {
        this.torque = torque;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Set<Driver> getDrivers() {
        return drivers;
    }

    public void setDrivers(Set<Driver> drivers) {
        this.drivers = drivers;
    }

    public Set<Race> getRaces() {
        return races;
    }

    public void setRaces(Set<Race> races) {
        this.races = races;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(id_car, car.id_car) && Objects.equals(modelName, car.modelName) && Objects.equals(horsepower, car.horsepower) && Objects.equals(torque, car.torque) && Objects.equals(team, car.team) && Objects.equals(drivers, car.drivers) && Objects.equals(races, car.races);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_car, modelName, horsepower, torque, team, drivers, races);
    }
}

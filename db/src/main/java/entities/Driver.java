package entities;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "driver")
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_driver;

    private String firstName;

    private String lastName;

    private Integer age;

    private Integer driverPoints;

    @Enumerated(EnumType.STRING)
    private DriverType driverType;

    @ManyToOne
    private Team team;

    @ManyToOne
    private Car car;

    @OneToMany(mappedBy = "champion")
    private Set<Season> seasons;

    public Driver() {
    }

    public Driver(Long id_driver, String firstName, String lastName, Integer age, Integer driverPoints, DriverType driverType, Team team, Car car, Set<Season> seasons) {
        this.id_driver = id_driver;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.driverPoints = driverPoints;
        this.driverType = driverType;
        this.team = team;
        this.car = car;
        this.seasons = seasons;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Driver driver = (Driver) o;
        return Objects.equals(id_driver, driver.id_driver) && Objects.equals(firstName, driver.firstName) && Objects.equals(lastName, driver.lastName) && Objects.equals(age, driver.age) && Objects.equals(driverPoints, driver.driverPoints) && driverType == driver.driverType && Objects.equals(team, driver.team) && Objects.equals(car, driver.car) && Objects.equals(seasons, driver.seasons);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_driver, firstName, lastName, age, driverPoints, driverType, team, car, seasons);
    }

    public Long getId_driver() {
        return id_driver;
    }

    public void setId_driver(Long id_driver) {
        this.id_driver = id_driver;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getDriverPoints() {
        return driverPoints;
    }

    public void setDriverPoints(Integer driverPoints) {
        this.driverPoints = driverPoints;
    }

    public DriverType getDriverType() {
        return driverType;
    }

    public void setDriverType(DriverType driverType) {
        this.driverType = driverType;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Set<Season> getSeasons() {
        return seasons;
    }

    public void setSeasons(Set<Season> seasons) {
        this.seasons = seasons;
    }
}

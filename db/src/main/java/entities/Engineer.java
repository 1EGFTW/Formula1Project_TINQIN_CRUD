package entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "engineer")
public class Engineer {
    @Id
    @GeneratedValue
    private Long id_engineer;

    private String firstName;

    private String lastName;

    @ManyToOne
    private Team team;

    @ManyToOne
    private Position position;

    public Engineer() {
    }

    public Engineer(Long id_engineer, String firstName, String lastName, Team team, Position position) {
        this.id_engineer = id_engineer;
        this.firstName = firstName;
        this.lastName = lastName;
        this.team = team;
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Engineer engineer = (Engineer) o;
        return Objects.equals(id_engineer, engineer.id_engineer) && Objects.equals(firstName, engineer.firstName) && Objects.equals(lastName, engineer.lastName) && Objects.equals(team, engineer.team) && Objects.equals(position, engineer.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_engineer, firstName, lastName, team, position);
    }

    public Long getId_engineer() {
        return id_engineer;
    }

    public void setId_engineer(Long id_engineer) {
        this.id_engineer = id_engineer;
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

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}

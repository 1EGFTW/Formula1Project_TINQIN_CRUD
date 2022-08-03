package entities;

import lombok.EqualsAndHashCode;

import javax.persistence.*;
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
    private Team team;

    @OneToMany(mappedBy = "car")
    private Set<Driver> drivers;

    @OneToMany(mappedBy = "winner")
    private Set<Race> races;
}

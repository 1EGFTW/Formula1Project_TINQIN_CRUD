package entities;

import javax.persistence.*;
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
}

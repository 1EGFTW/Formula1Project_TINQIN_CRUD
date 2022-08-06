package com.tinqin.academy.db.entities;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "position")
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_position;

    private String positionName;

    @OneToMany(mappedBy = "position")
    private Set<Engineer> engineers;

    public Position(Long id_position, String positionName, Set<Engineer> engineers) {
        this.id_position = id_position;
        this.positionName = positionName;
        this.engineers = engineers;
    }

    public Position() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return Objects.equals(id_position, position.id_position) && Objects.equals(positionName, position.positionName) && Objects.equals(engineers, position.engineers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_position, positionName, engineers);
    }

    public Long getId_position() {
        return id_position;
    }

    public void setId_position(Long id_position) {
        this.id_position = id_position;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public Set<Engineer> getEngineers() {
        return engineers;
    }

    public void setEngineers(Set<Engineer> engineers) {
        this.engineers = engineers;
    }
}

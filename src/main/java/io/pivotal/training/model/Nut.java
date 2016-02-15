package io.pivotal.training.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Nut {

    @GeneratedValue
    @Id
    private Long id;

    private String name;

    public Nut() {
    }

    public Nut(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }
}

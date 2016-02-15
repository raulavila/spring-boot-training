package io.pivotal.training.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Nut {

    @Id
    private long id;
    private String name;

    public Nut() {
    }

    public Nut(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

package com.item.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import io.micronaut.core.annotation.Introspected;

@Entity
@Introspected
public class Item {

    static final String COLUMN_ID = "ID";

    @Id
    @Column(name = COLUMN_ID)
    private String id;

    @Column(name = "DESCRIPTION")
    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

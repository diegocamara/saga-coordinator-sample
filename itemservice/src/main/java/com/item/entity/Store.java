package com.item.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Store {

    static final String COLUMN_ID = "ID";

    @Id
    @Column(name = COLUMN_ID)
    private String id;

    @OneToOne
    @JoinColumn(name = "ITEM_ID", referencedColumnName = Item.COLUMN_ID)
    private Item item;

    @Column(name = "QUANTITY")
    private int quantity;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

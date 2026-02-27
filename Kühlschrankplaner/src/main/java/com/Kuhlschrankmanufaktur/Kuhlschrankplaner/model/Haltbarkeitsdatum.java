package com.Kuhlschrankmanufaktur.Kuhlschrankplaner.model;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
@Entity
public class Haltbarkeitsdatum {
    @Id
    private Date datum;

    @OneToMany(mappedBy = "haltbarkeit")
    private List<Item> items;

    public Haltbarkeitsdatum(Date datum) {
        this.datum = datum;
        items = new ArrayList<>();
    }

    protected Haltbarkeitsdatum(){}
    
    protected void itemHinzufügen(Item newItem) {
        if (!this.items.contains(newItem)) {
            this.items.add(newItem);
            newItem.haltbarkeitAendern(this);
        }
    }
}
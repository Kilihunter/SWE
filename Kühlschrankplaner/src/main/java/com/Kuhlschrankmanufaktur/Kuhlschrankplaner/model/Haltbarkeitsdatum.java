package com.Kuhlschrankmanufaktur.Kuhlschrankplaner.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

public class Haltbarkeitsdatum {
    @Id
    private Date datum;

    @OneToMany(mappedBy = "haltbarkeitsdatum")
    private List<Item> items;

    Haltbarkeitsdatum(Date datum) {
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
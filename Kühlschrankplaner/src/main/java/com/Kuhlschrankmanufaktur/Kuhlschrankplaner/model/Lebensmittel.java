package com.Kuhlschrankmanufaktur.Kuhlschrankplaner.model;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue; 
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Lebensmittel {
    @Id
    @GeneratedValue
    private int id;    

    @OneToMany(mappedBy = "lebensmittel")
    private List<Item> items;

    @ManyToOne
    @JoinColumn(name = "kategorie_id")
    private Kategorie kategorie;

    @Column(unique=true)
    private String name;

    private String einheit;

    private int currentAnzahl;

    private int minimalAnzahl;

    public Lebensmittel(Kategorie kategorie, String name, int minimalAnzahl) {
        kategorie.lebensmittelHinzufügen(this);
        this.name = name;
        this.currentAnzahl = 0;
        this.minimalAnzahl = minimalAnzahl;
        items = new ArrayList<>();
    }
    protected Lebensmittel(){}

    public void Namenändern(String name){
        this.name = name;
    }

    public void gegessen(Item item){
        if (items.contains(item)) {
            items.remove(item);
            currentAnzahl--;
        }
    }
    public void eingekauft(Item item){
        if (!items.contains(item)) {
            items.add(item);
            item.lebensmittelAendern(this);
            currentAnzahl++;
        }
    }
    protected void kategorieAendern(Kategorie neueKategorie) {
        if(neueKategorie != null && neueKategorie != kategorie) {
            kategorie = neueKategorie;
        }
    }

}

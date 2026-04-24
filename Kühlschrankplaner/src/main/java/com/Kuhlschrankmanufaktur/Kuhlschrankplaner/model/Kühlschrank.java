package com.Kuhlschrankmanufaktur.Kuhlschrankplaner.model;

import java.util.*;
import jakarta.persistence.*;

@Entity
public class Kühlschrank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "kühlschrank", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Item> items = new ArrayList<>();

    protected Kühlschrank() {
    }

    public Kühlschrank(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Der Kühlschrank braucht einen Namen.");
        }
        this.name = name;
    }

    
    public Long getId() { return id; }
    public String getName() { return name; }

    public List<Item> getItems() {
        return Collections.unmodifiableList(items);
    }

    public void itemHinzufuegen(Item item) {
        if (item == null) throw new IllegalArgumentException("Item darf nicht null sein.");
        items.add(item);
        item.setKühlschrank(this); 
    }

    public boolean itemVerbrauchen(Long itemId, int verbrauchteAnzahl) {
        for (Item item : items) {
            if (item.getId() != null && item.getId().equals(itemId)) {
                int neueAnzahl = item.getMenge().getAnzahl() - verbrauchteAnzahl;
                
                if (neueAnzahl > 0) {
                    item.mengeAnpassen(neueAnzahl);
                    return true;
                } else if (neueAnzahl < 0) {
                    throw new IllegalArgumentException("Es können nicht mehr Items verbraucht werden als vorhanden sind.");
                } else {
                    items.remove(item);
                    item.setKühlschrank(null);
                    return true;
                }
            }
        }
        return false;
    }

    public long bestandVon(String lebensmittelName) {
        return items.stream()
                .filter(i -> i.getLebensmittel().getName().equalsIgnoreCase(lebensmittelName))
                .mapToLong(i -> i.getMenge().getAnzahl())
                .sum();
    }

    public List<Item> getAbgelaufeneItems() {
        return items.stream()
                .filter(Item::istAbgelaufen)
                .toList();
    }

    public Map<Lebensmittel, Integer> getBestandUebersicht() {
        Map<Lebensmittel, Integer> bestand = new HashMap<>();
        for (Item item : items) {
            bestand.merge(item.getLebensmittel(), item.getMenge().getAnzahl(), Integer::sum);
        }
        return Collections.unmodifiableMap(bestand); 
    }
}
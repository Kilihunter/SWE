package com.Kuhlschrankmanufaktur.Kuhlschrankplaner.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "kuehlschrank")
public class Kühlschrank {

    @Id
    @GeneratedValue
    private Integer id;

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

    public Integer getId() { return id; }
    public String getName() { return name; }

    public List<Item> getItems() {
        return Collections.unmodifiableList(items);
    }

    public void itemHinzufuegen(Item item) {
        if (item == null) throw new IllegalArgumentException("Item darf nicht null sein.");
        items.add(item);
        item.setKühlschrank(this);
    }

    public void itemVerbrauchen(Integer itemId, int verbrauchteAnzahl) {
        Item gefundenesItem = items.stream()
                .filter(item -> item.getId() != null && item.getId().equals(itemId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Item mit ID " + itemId + " nicht gefunden."));

        int neueAnzahl = gefundenesItem.getMenge().getAnzahl() - verbrauchteAnzahl;

        if (neueAnzahl > 0) {
            gefundenesItem.mengeAnpassen(neueAnzahl);
        } else if (neueAnzahl < 0) {
            throw new IllegalArgumentException("Es können nicht mehr Items verbraucht werden als vorhanden sind.");
        } else {
            this.itemEntfernen(itemId);
        }
    }
    public void itemEntfernen(Integer itemId) {
        Item itemZumEntfernen = items.stream()
                .filter(item -> item.getId() != null && item.getId().equals(itemId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Item mit ID " + itemId + " nicht gefunden."));
        items.remove(itemZumEntfernen);
        itemZumEntfernen.setKühlschrank(null);
    }

    public Integer bestandVon(String lebensmittelName) {
        return items.stream()
                .filter(i -> i.getLebensmittel().getName().equalsIgnoreCase(lebensmittelName))
                .mapToInt(i -> i.getMenge().getAnzahl())
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
            bestand.merge(item.getLebensmittel(), item.getMenge().getAnzahl(), (a, b) -> a + b);
        }
        return Collections.unmodifiableMap(bestand);
    }
}

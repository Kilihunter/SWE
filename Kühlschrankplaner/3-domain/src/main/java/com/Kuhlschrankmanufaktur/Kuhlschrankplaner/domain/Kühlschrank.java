package com.Kuhlschrankmanufaktur.Kuhlschrankplaner.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    public void itemVerbrauchen(Item item, int verbrauchteAnzahl) {
        
        if (verbrauchteAnzahl <= 0) {
            throw new IllegalArgumentException("Verbrauchte Anzahl muss positiv sein.");
        }
        int neueAnzahl = item.getAnzahl() - verbrauchteAnzahl;

        if (neueAnzahl > 0) {
            item.mengeAnpassen(neueAnzahl);
        } else if (neueAnzahl < 0) {
            throw new IllegalArgumentException("Es können nicht mehr Items verbraucht werden als vorhanden sind.");
        } else {
            this.itemEntfernen(item);
        }
    }
    public void itemEntfernen(Item itemZumEntfernen) {
        items.remove(itemZumEntfernen);
        itemZumEntfernen.setKühlschrank(null);
    }

    public Integer bestandVon(Lebensmittel lebensmittel) {
        return items.stream()
                .filter(i -> i.getLebensmittel().equals(lebensmittel))
                .mapToInt(Item::getAnzahl)
                .sum();
    }

    public List<Item> getAbgelaufeneItems() {
        return items.stream()
                .filter(Item::istAbgelaufen)
                .toList();
    }

    public List<Item> getBestandUebersicht() {
        return items;
    }
    public void itemKorrigieren(Item item, Item korrigiertesItem) {

        item.korrigieren(
                korrigiertesItem.getLebensmittel(),
                korrigiertesItem.getHaltbarkeit(),
                korrigiertesItem.getAnzahl(),
                korrigiertesItem.getLebensmittel().getEinheit()
        );
    }   
    public Item findeItemNachId(Integer itemId) {
        return items.stream()
                .filter(item -> item.getId() != null && item.getId().equals(itemId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "Item mit ID " + itemId + " nicht gefunden."
                ));
    }
}

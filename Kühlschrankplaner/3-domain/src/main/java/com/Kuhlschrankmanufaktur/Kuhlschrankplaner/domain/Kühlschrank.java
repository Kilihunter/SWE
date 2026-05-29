package com.Kuhlschrankmanufaktur.Kuhlschrankplaner.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.*;

@Entity
@Table(name = "kuehlschrank")
public class Kühlschrank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public void itemKorrigieren(Item item, Lebensmittel lebensmittel, Haltbarkeitsdatum haltbarkeit, int anzahl) {
        item.korrigieren(lebensmittel, haltbarkeit, anzahl, lebensmittel.getEinheit());
    }   
    public Item findeItemNachId(Integer itemId) {
        return items.stream()
                .filter(item -> item.getId() != null && item.getId().equals(itemId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "Item mit ID " + itemId + " nicht gefunden."
                ));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Kühlschrank k)) return false;
        return id != null && id.equals(k.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

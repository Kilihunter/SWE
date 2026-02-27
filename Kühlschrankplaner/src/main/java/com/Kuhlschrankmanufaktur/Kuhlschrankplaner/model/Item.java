package com.Kuhlschrankmanufaktur.Kuhlschrankplaner.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Item {
  @Id
  @GeneratedValue
  private int id;

  @ManyToOne
  @JoinColumn(name = "haltbarkeit_id")
  private Haltbarkeitsdatum haltbarkeit;

  @ManyToOne
  @JoinColumn(name = "lebensmittel_id")
  private Lebensmittel lebensmittel;

  public Item(Haltbarkeitsdatum haltbarkeit, Lebensmittel lebensmittel) {
    lebensmittel.eingekauft(this);
    haltbarkeit.itemHinzufügen(this);
  }
  protected Item(){}

  protected void haltbarkeitAendern(Haltbarkeitsdatum neueHaltbarkeit) {
    if(neueHaltbarkeit != null && neueHaltbarkeit != haltbarkeit) {
        haltbarkeit = neueHaltbarkeit;
    }
  }
  protected void lebensmittelAendern(Lebensmittel neuesLebensmittel) {
    if(neuesLebensmittel != null && neuesLebensmittel != lebensmittel) {
        lebensmittel = neuesLebensmittel;
    }
  }

}


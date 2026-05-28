package com.Kuhlschrankmanufaktur.Kuhlschrankplaner.adapters.Resources;

import java.time.LocalDate;

public class ItemResource {
    
    public Integer Itemid;
    public String lebensmittelName;
    public Integer anzahl;
    public LocalDate haltbarkeit;

    public ItemResource(Integer id, String lebensmittelName, Integer anzahl, LocalDate haltbarkeit) {
        this.Itemid = id;
        this.lebensmittelName = lebensmittelName;
        this.anzahl = anzahl;
        this.haltbarkeit = haltbarkeit;
    }

    
    public LocalDate getHaltbarkeit() {
        return haltbarkeit;
    }
    public Integer getItemid() {
        return Itemid;
    }
    public Integer getAnzahl() {
        return anzahl;
    }
    public String getLebensmittelName() {
        return lebensmittelName;
    }
}

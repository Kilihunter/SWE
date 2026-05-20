package com.Kuhlschrankmanufaktur.Kuhlschrankplaner.adapters.Resources;

import java.util.Map;

public class EinkaufslisteResource {

    public Integer id;
    public String name;
    public Map<String, Integer> eintraege;

    public EinkaufslisteResource(Integer id, String name, Map<String, Integer> eintraege) {
        this.id = id;
        this.name = name;
        this.eintraege = eintraege;
    }

    public Integer getId() { return id; }
    public String getName() { return name; }
    public Map<String, Integer> getEintraege() { return eintraege; }
}

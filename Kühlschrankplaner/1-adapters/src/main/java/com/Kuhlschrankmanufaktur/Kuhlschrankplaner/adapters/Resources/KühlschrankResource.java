package com.Kuhlschrankmanufaktur.Kuhlschrankplaner.adapters;

import java.util.List;

public class KühlschrankResource {
    public Integer id;
    public String name;
    public List<ItemResource> items;

    public KühlschrankResource(Integer id, String name, List<ItemResource> items) {
        this.id = id;
        this.name = name;
        this.items = items;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    
    public List<ItemResource> getItems() {
        return items;
    }
}
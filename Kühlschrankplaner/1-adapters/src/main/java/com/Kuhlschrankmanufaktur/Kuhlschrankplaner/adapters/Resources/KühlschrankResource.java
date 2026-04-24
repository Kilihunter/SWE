package com.Kuhlschrankmanufaktur.Kuhlschrankplaner.adapters;

public class KühlschrankResource {
    public Integer id;
    public String name;

    public KühlschrankResource(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
} 
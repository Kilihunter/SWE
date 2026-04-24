package com.Kuhlschrankmanufaktur.Kuhlschrankplaner.adapters;

public class KühlschrankResource {
    public long id;
    public String name;

    public KühlschrankResource(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
} 
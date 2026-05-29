package com.Kuhlschrankmanufaktur.Kuhlschrankplaner.adapters.Resources;

public class EinkaufslistenEintragErstellenResource {

    public String name;
    public int menge;

    public EinkaufslistenEintragErstellenResource(String name, int menge) {
        this.name = name;
        this.menge = menge;
    }

    public String getName() {
        return name;
    }

    public int getMenge() {
        return menge;
    }
}
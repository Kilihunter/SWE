
/*package com.Kuhlschrankmanufaktur.Kuhlschrankplaner.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.Kuhlschrankmanufaktur.Kuhlschrankplaner.model.Kategorie;
import com.Kuhlschrankmanufaktur.Kuhlschrankplaner.model.Lebensmittel;
import com.Kuhlschrankmanufaktur.Kuhlschrankplaner.repository.LebensmittelRepository;


@Service
public class LebensmittelService {

    private final LebensmittelRepository lebensmittelRepository;

    public LebensmittelService(LebensmittelRepository lebensmittelRepository) {
        this.lebensmittelRepository = lebensmittelRepository;
    }
    // lebensmittel hinzufügen
    private  Lebensmittel Anlegen(Kategorie kategorie, String name, int minimalAnzahl) {
        return lebensmittelRepository.save(new Lebensmittel(kategorie, name, minimalAnzahl));
    }   
    public Lebensmittel lebensmittelHinzufügen(Kategorie kategorie, String name, int minimalAnzahl) {
        Lebensmittel lebensmittel = findByName(name);
        if (lebensmittel == null) {
            lebensmittel = Anlegen(kategorie, name, minimalAnzahl);
        }
        return lebensmittel;
    }
    //Suchen
     public Lebensmittel findByName(String name) {
        return lebensmittelRepository.findByName(name).orElse(null);
    }
    public List<Lebensmittel> findAll() {
        return lebensmittelRepository.findAll();
    }
    // löschen
    public void deleteByName(String name) {
        Lebensmittel lebensmittel = findByName(name);
        if (lebensmittel != null) {
            lebensmittelRepository.delete(lebensmittel);
        }
    }
}
    */
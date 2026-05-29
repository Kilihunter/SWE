# Kühlschrankplaner

## Funktionalitäten

Die Anwendung unterstützt folgende Funktionen:

- Kühlschrank anlegen
- alle Kühlschränke anzeigen
- einzelnen Kühlschrank anzeigen
- Item in einen Kühlschrank legen
- Item teilweise verbrauchen
- Item vollständig entfernen
- Item korrigieren, wenn Attribute falsch eingegeben wurden
- Bestand durchsuchen
- Bestand nach Kategorie filtern
- Bestand nach Status filtern, z. B. `OK` oder `ABGELAUFEN`
- Bestand nach Name oder Haltbarkeit sortieren
- ablaufende Items anzeigen (Items deren Haltbarkeitsdatum innerhalb der nächsten X Tage liegt, default: 3 Tage)
- Einkaufsliste anlegen
- Einkaufslisten anzeigen
- Einträge auf eine Einkaufsliste schreiben
- Einkauf verarbeiten, sodass gekaufte Items in den Kühlschrank übernommen und von der Einkaufsliste entfernt werden
- abgelaufene Items aus dem Kühlschrank entfernen und automatisch auf die Einkaufsliste schreiben
- Mindestbestand prüfen und fehlende Lebensmittel automatisch auf die Einkaufsliste schreiben
# Anwendung starten

DB starten im Root-Ordner Kühlschrankplaner
```bash
docker compose up -d
```

Zuerst im Root-Ordner Kühlschrankplaner das Projekt bauen:
```bash
mvn clean install
```

Danach in das Plugin-Modul wechseln:

```bash
cd 0-plugins
```

Dann die Spring-Boot-Anwendung starten:

```bash
mvn spring-boot:run
```

Nach dem Start ist die Anwendung erreichbar unter:

```text
http://localhost:8080
```

DB stoppen
```bash
docker compose down
```

DB stoppen UND Daten löschen
```bash
docker compose down -v
```

## Tests ausführen

Die ersten 10 Unit Tests liegen in der Application-Schicht.

Dazu aus dem Root-Ordner in das Application-Modul wechseln:

```bash
cd 2-application
```

Dann die Tests ausführen:

```bash
mvn test
```
Die restlichen 8 Tests liegen in der Domain-Schicht 
```bash
cd 3-domain
```

Dann die Tests ausführen:

```bash
mvn test
```
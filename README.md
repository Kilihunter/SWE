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
- ablaufende Items anzeigen
- Einkaufsliste anlegen
- Einkaufslisten anzeigen
- Einträge auf eine Einkaufsliste schreiben
- Einkauf verarbeiten, sodass gekaufte Items in den Kühlschrank übernommen und von der Einkaufsliste entfernt werden
- abgelaufene Items aus dem Kühlschrank entfernen und automatisch auf die Einkaufsliste schreiben
  
## Datenbank

DB starten (im Kühlschrankplaner-Verzeichnis)
docker compose up -d

DB stoppen
docker compose down

DB stoppen UND Daten löschen
docker compose down -v

## Anwendung starten

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

## Tests ausführen

Die 10 Unit Tests liegen in der Application-Schicht.

Dazu aus dem Root-Ordner in das Application-Modul wechseln:

```bash
cd 2-application
```

Dann die Tests ausführen:

```bash
mvn test
```

# Kühlschrankplaner

Der Kühlschrankplaner ist eine Spring-Boot-Anwendung zur Verwaltung von Kühlschränken.

Ein `Lebensmittel` beschreibt dabei die Art des Lebensmittels, zum Beispiel `Steak`, `Gurke` oder `Milch`. Ein `Item` ist dagegen ein konkreter Bestandseintrag im Kühlschrank, zum Beispiel `2 kg Fleisch`, `3 Gurken` oder `1 Packung Milch`.

Items können mit Name, Kategorie, Anzahl, Einheit und Haltbarkeitsdatum gespeichert und verwaltet werden. Zusätzlich gibt es Suchfunktionen und Einkaufslisten.

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

Die Anwendung verwendet MySQL.

Damit die Anwendung gestartet werden kann, muss eine MySQL-Instanz auf Port `3306` laufen. Alternativ kann die Datenbankanbindung in folgender Datei an die lokale Datenbank angepasst werden:
```text
0-plugins/src/main/resources/application.properties
```

Außerdem muss eine Datenbank mit dem Namen `kuehlschrank` existieren.

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

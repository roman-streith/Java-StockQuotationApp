# Protokoll Algorithmen und Datenbanken Übung 1

## Obejkt Aktie

## Die Hashfunktion
 Dei Hashfunktion welche jedem NEUEN eintrag einen Index im Hashtabel zuordnet ist wie folgt aufgebaut:
 Es wird eine variable namen "index" mit dem wert "0" initilalisiert. Danach wird mit hilfe einer Schleife 
 Jeder Buschstaben des Aktiennames in folgender Form ein Wert gegeben welcher in zur Summer aller Buchstabenwerte hinzugezählt wird.
 
 - Zuerst wird er asciiwert des aktuellen Buchstaben ermittelt
 - danach wird die Zahl 53 hoch dem aktuellen index (wie in der Informatik gewohnt starten wir bei 0) genommen.
 - diese beiden werde werden anschließend multipliziert und zu der variablen "index" hinzugezählt.
 - Daraus ergibt sich folgende Formel "index" = SUMME (i=0 -> n) asciiWert(Wort[i])*53^i
 - Sobald das gesamt Wort auf diese art und weise abgearbeitet wurde wird dieser Wert mit dem modulo der Hashtablelänge genommen.
 
 Danach wird auf Kollision geprüft.


## Kollisionserkennung
 Nachdem der Hashwert ermittelt wurde wird geprüft ob der gehashte index des Hashtabels auch tatsächlich leer (oder in diesem Fall "null") ist.
 Dies wird in einer While-Schleife geprüft, sollte es sich um eine feld des Hashtables handeln welcher NICHT "null" ist wird folgender Algorithmus angewandt:
 - Wenn der Aktiennamen der bereits eingetragenen Aktie ident mit der ist welche wir versuchen einzutragen wird die Schleife gestoppt und dem Benutzer mitgeteilt, dass die Aktie bereits existiert.
 - Sollte der Wert an dem index "null" sein wird die Schleife gestoppt und der wert wird in den Hashtable eingetragen.
 - andernfalls wird der index zu dem index folgeder Wert addiert "(val + 1) * (val + 1)" wobei die variable val vor beginn der Schleife initialisiert
   wird und bei jedem durchlauf um eines erhöht wird um so eine bessere Streuung der Einträge zu erreichen

## WKN Hashtable
 Nachdem eine Aktien ind en Aktien-Hashtable eingetragen wird, wird die WKN und der index an welchem die Aktie im Aktien-Hashtable 
 eingetragen wurde in einen weiteren Hashtabel eingetragen.
 -
 -
 -

## Verwaltung der Kursdaten
 Bei dem Befehlt "IMPORT" werden kursdaten einer Aktien anhande dessen WKN dem Objekt Aktie hinzugefügt.
 Diese werte werden aus einer csv Datei eingelesen und in einer "String Array" gespeichert.
 Bei aufruf des Aktie wir geprüft ob es Einträge in der besagten String Array gibt und gegebenenfalls der aktuellste dargestellt.

##Löschalgorithmus

##Aufwandsabschätzung



# mtcg
Die Hauptbereiche im Projekt sind in drei Packages unterteilt. Model, Controller und 
Service.
Der Server nimmt Client-Requests (in dem Fall vom Curl-Script) auf und schickt sie dem RequestHandler weiter, die 
Requests werden dann in einem Request-Objekt 
gespeichert und zur „APP“ weitergegeben. Dort wird je 
nach Pfad die zugehörige Funktion des zugehörigen 
Controllers aufgerufen. Die Controller leiten die Anfragen
an die entsprechenden Service-Klassen weiter. In der 
Service-Klasse wird der Request bearbeitet und in 
die Datenbank eingetragen. Anschließend schickt der 
Controller eine Response an den Client zurück.

# Atelier2 - FindYourWay

## Directives d'installation 

###Docker
Recupérer les 3 fichiers :
(Dockerfile, docker-entrypoint.sh postgresql-9.4.1212.jar)
<br/>  • docker build -t postgres . 
<br/>  • docker start bd-postgres

###Postgres
Création d'un User pour Postgres:
<br/>  • docker run --name bd-postgres -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=riovas -p 5432:5432 -d postgres

Run la BD-Postgres:
<br/>  • docker run -it --rm --link bd-postgres:postgres postgres psql -h bd-postgres -U postgres

Import de la BD postgres:
<br/>  • psql -U postgres bd-postgres < database.sql

###Wildfly
Ajoute du Driver postgresql au Serveur Wildfly:
<br/>  • Connection au serveur Wilfly 127.0.0.1:8080, switch sur la console http://localhost:9990/console
Deployment -> add -> Upload a new deployement, choisir le driver .jar	 
Ensuite Configuration -> Subsystems -> Datasources -> Non-XA -> add -> Choose Datasource : PostgreSQL datasource

Create Datasource: name: PostgresDS
			  <br/>JNDI Name: java:/PostgresDS

JDBC Driver -> Detected driver -> postgresql-9.4.1212.jar

Connection URL : jdbc:postgresql://127.0.0.1:5432/postgres

Log pour postgres: name: postgres 
			  <br/>password: riovas 


## API

### BackEnd 

####Admin

POST /api/privee/points
<br/>  • Description: Ajout d'un point
<br/>  • Params:  {"lat": ..., "lng": ..., "appellation": ...}

POST /api/privee/destinations
<br/>  • Description: Ajout d'une destinations
<br/>  • Params:  {"lat": ..., "lng": ..., "description": ..., "lieu": ...}

####Map

POST /api/parties
<br/>  • Description: Créer une partie
<br/>  • Params:  {"nom": ..., "description": ...}

GET /api/parties/point
<br/>  • Description: Retourne l'appellation du point
<br/>  • Params:  Aucun paramètre supplémentaire requis

POST /api/parties/points
<br/>  • Description: Retourne un indice sur l'indice final
<br/>  • Params:  {"lat": ..., "lng": ...}

POST /api/parties/destination
<br/>  • Description: Retourne le score de la partie
<br/>  • Params:  {"lat": ..., "lng": ...}

##Déroulement


| Client |  | Serveur |
| :----------- | :------: | ------------: |
| /api/parties | --> | Créer Partie + Token |
| Token | <-- | Renvoie un Token |
| ... | ... | ... |										
| /api/parties/point + Token | --> | Récupère le point i|
| Appellation i | <-- | Renvoie l'appellation du point |
| /api/parties/points + Token| --> | Récupère et vérifie les coordonnées(lat, lng) |
| Indice | <-- | Renvoie l'indice de la destination, null si incorrecte  |
| ... | ... | ... |
| /api/parties/destination | --> | Récupère et vérifie les coordonnées de la destination |
| Score | <-- | Renvoie un score |

<br/> i : indice d'itération
<br/>Le bloque entre les ... est répéter 5 fois qui correspond au placement des 5 points sur la carte et le 6ème étant la destination








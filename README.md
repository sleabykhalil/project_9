# project_9


## Name
Patient UI : c'est un outil pour aider les médecins à identifier les patients les plus à risque d'avoir le diabète de type 2

## Getting started

Comment exécuter l'application :
### 1- Build les projet
Il y a 3 projets
patientApi , patientMedicalHistory-Microservice , patientui
pour chaque projet :
```
cd patientApi
mvn clean install
cd ..
cd patientMedicalHistory-Microservice
mvn clean install
cd ..
cd patientui
mvn clean install
cd ..

```
### 2- Docker
Docker pour production:
```
docker-compose -f .\docker-compose.yml up -d
```

### 3- Init avec data et les cas de tests
1- SqlDB avec le logiciel adminer

http://adminer:9090/

Ou sur Windows OS

http://localhost:9090/

      Database System : PostgreSql
      Username : admin
      Password : change_me
      Database : mediscreen_sql


2- Copiez-collez les commandes qui se trouvent dans le fichier
initData/CreatePatientDB.sql

3- Pour ajouter les données de test, utilisez les commandes curl qui se trouvent dans le fichier
initData/Test_data.sh
initData/real_data.sh


# Home Page

[http://microservice.patientui:8080/](http://microservice.patientui:8080)
Ou sur windows OS
[http://localhost:8080/](http://localhost:8080)

# Swagger:
### patientApi
[patientApi Swagger ui](http://microservice.patient:8081/swagger-ui/index.html)
### patientMedicalHistory-Microservice
[patientMedicalHistory-Microservice Swagger ui](http://microservice.patientmedicalhistory:8082/swagger-ui/index.html)
### patientui
[patientui Swagger ui](http://microservice.patientui:8080/swagger-ui/index.html#/)

#### Pour lancer Swagger sur local windows OS il faut ajouter dans le hosts

```
127.0.0.1 microservice.patient

127.0.0.1 microservice.patientmedicalhistory

127.0.0.1 microservice.patientui
```

# Patient UI Architectural Overview


![Patient UI Architectural Overview](https://user-images.githubusercontent.com/64974948/161336947-1eb396a2-698d-41c8-902e-31edf99bdc1b.png)


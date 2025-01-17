
---

# README - Projet 2024 <u>Programmeur</u>

## Instructions pour lancer l'application

### Prérequis
- <b>Langage/Framework utilisé</b>
    - Apache Maven
    - Java (JDK au moins 17)
    - PostgreSQL
- <b>Via Maven</b>
    - Les dépendances nécessaires (PostgreSQL, JavaFX, JUnit) seront automatiquement téléchargées lors de la compilation avec Maven.

### Étapes pour lancer le projet

1. **Via un IDE**  
   Si vous utilisez un IDE (comme IntelliJ IDEA ou Eclipse), vous pouvez exécuter directement votre classe principale depuis l’environnement graphique.

2. **Via la ligne de commande**  
   Vous pouvez exécuter le projet, mais vous devez avoir Maven installé sur votre machine :
   ```bash
   mvn clean compile
   mvn exec:java -Dexec.mainClass="org.example.Start"
   ```

### <b><span style="color:red;"> Information Cruciale ! </span></b>
Assurez-vous que PostgreSQL est installé et configuré.  
Créez une base de données si nécessaire :
```sql
CREATE DATABASE prog_bd;
```
#### Attention
Lors de la création d'une base de données, PostgreSQL la place en minuscules par défaut. Si nécessaire, ajustez les informations de connexion dans le fichier [DbService](src/main/java/org/example/database/DbService.java).

### <u>Choix du SGBD</u>
Nous avons opté pour **[PostgreSQL]**.

---

## Fonctionnalités non implémentées

Voici les fonctionnalités que nous n’avons pas pu réaliser dans les délais impartis :

1. **[Historique en BDD]**  
   **Pourquoi ?**
    - L’idée de l’historique est venue tard : nous avions d’abord opté pour une interface graphique. Une fois celle-ci terminée, nous nous sommes penchés sur l’historique. Il est présent dans l’application mais n’est pas sauvegardé en base de données. À terme, nous voulions aussi offrir la possibilité d’annuler des requêtes, mais cela est devenu trop complexe par manque de temps.

2. **[BDD embarquées]**  
   **Pourquoi ?**
    - Nous voulions intégrer une base de données embarquée (c’est-à-dire directement incluse dans l’application Java) ou qui fonctionne sans configuration complexe, pour que l’application puisse tourner dans tous les cas. Cependant, le projet ayant déjà été finalisé avec PostgreSQL et compte tenu du temps disponible, nous n’avons pas mis en place cette fonctionnalité.

---

## Fonctionnalités supplémentaires ajoutées

Voici les fonctionnalités supplémentaires développées par rapport au cahier des charges initial :

1. **[Historique]**
    - **Description :** Nous avons ajouté un historique à l’application permettant de visualiser les requêtes (il n’est pas sauvegardé en base de données).

2. **[Mode Test]**
    - **Description :** Nous avons créé une classe de test (qui n’est pas en JUnit) pour manipuler l’application comme un utilisateur classique. Elle nous a aidés pour le débogage et peut aussi vous aider.

3. **[Une interface]**
    - **Description :** Le but était de créer une interface rassemblant toutes les fonctionnalités de base, sans les inconvénients du terminal (où les informations peuvent vite devenir illisibles, surtout sur plusieurs lignes).

---

## Informations supplémentaires
- **Nom et prénom :** Touré Mehédi, Adil Chetouani
- **Groupe :** LSI_2
---
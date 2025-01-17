------------------------------------------------------------
README - Projet 2024 PROGRAMMEURS

Instructions pour lancer l'application
------------------------------------------------------------
Prérequis
Langage/Framework utilisés :
    - Apache Maven
    - Java (JDK au moins 17)
    - PostgreSQL

Via Maven :
    - Les dépendances nécessaires (PostgreSQL, JavaFX, JUnit) seront automatiquement téléchargées lors de la compilation avec Maven.

Étapes pour lancer le projet
    Via un IDE :
        - Si vous utilisez un IDE (comme IntelliJ IDEA ou Eclipse), vous pouvez exécuter directement votre classe principale depuis l’environnement graphique.

    Via la ligne de commande :
        - Vous pouvez exécuter le projet, mais vous devez avoir Maven installé sur votre machine.
          ______________________________________________
          mvn clean compile
          mvn exec:java -Dexec.mainClass="org.example.Start"
          ______________________________________________

------------------------------------------------------------
Information Cruciale !!!!!!!
    Assurez-vous que PostgreSQL est installé et configuré.
    Créez une base de données si nécessaire (le cas ou al table existe pas est gére dans le code):
        ______________________________
            CREATE DATABASE prog_bd;
        ______________________________
    Attention, lorsqu’on crée une base de données, elle est souvent en minuscules par défaut.
    Au besoin, adaptez les informations de connexion dans le fichier DbService
    (src/main/java/org/example/database/DbService.java)

------------------------------------------------------------
Choix du SGBD : PostgreSQL
------------------------------------------------------------
Fonctionnalités non implémentées
Voici les fonctionnalités que nous n'avons pas pu réaliser dans les délais impartis :

    Historique en BDD
        - L’idée de l’historique est venue tard. Nous avions d’abord opté pour développer principalement une interface, puis,
          une fois celle-ci terminée, nous nous sommes penchés sur l’historique. Il est présent dans l’application mais
          n’est pas sauvegardé en BDD. À terme, nous souhaiterions aussi ajouter la possibilité d’annuler des requêtes,
          mais cela devenait trop complexe par manque de temps.

    BDD embarquée
        - Nous voulions intégrer une base de données embarquée (c’est-à-dire directement incluse dans l’application Java) ou
          qui fonctionne sans configuration complexe, afin qu’elle puisse tourner en toutes circonstances. Cependant,
          le projet ayant déjà été finalisé avec PostgreSQL et par manque de temps, nous n’avons pas réalisé cette intégration.

------------------------------------------------------------
Fonctionnalités supplémentaires ajoutées
Voici les fonctionnalités supplémentaires développées par rapport au cahier des charges initial :

    Historique
        - Nous avons ajouté un historique à l’application permettant de visualiser les requêtes.
          (Il n’est pas sauvegardé en BDD.)

    Mode Test
        - Nous avons créé une classe de test (qui n’utilise pas JUnit) et manipule l’application
          comme le ferait un utilisateur classique. Elle nous a été utile pour le débogage,
          et pourrait également vous servir.

    Une interface
        - Le but était de développer une interface reprenant toutes les fonctionnalités de base,
          sans les inconvénients du terminal (qui peut vite devenir illisible, surtout avec des retours à la ligne).

------------------------------------------------------------
Informations supplémentaires
Nom et prénom : Touré Mehédi, Adil Chetouani
Groupe : LSI_2
------------------------------------------------------------

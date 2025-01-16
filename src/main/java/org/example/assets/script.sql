-- CREATE DATABASE sgbdproject;

-- \c sgbdproject;

CREATE TABLE programmeur (
     id SERIAL PRIMARY KEY,       -- ID unique auto-incrémenté
     nom VARCHAR(100) NOT NULL,   -- Nom du programmeur
     prenom VARCHAR(100) NOT NULL,-- Prénom du programmeur
     adresse VARCHAR(255),        -- Adresse
     pseudo VARCHAR(50) NOT NULL, -- Pseudo unique
     responsable INT,     -- Nom du responsable
     hobby VARCHAR(50),           -- Hobby du programmeur
     annNaissance INT,            -- Année de naissance
     salaire INT,                 -- Salaire annuel
     prime INT                    -- Prime annuelle
);

INSERT INTO programmeur (nom, prenom, adresse, pseudo, responsable, hobby, annNaissance, salaire, prime) VALUES
     ('Dupont', 'Alice', '123 Rue de Paris', 'alice123', 'Martin', 'Lecture', 1990, 50000, 5000),
     ('Durand', 'Bob', '45 Avenue des Champs', 'bobD45', 'Marie', 'Musique', 1985, 60000, 7000),
     ('Moreau', 'Charlie', '78 Boulevard Haussmann', 'charlieM', 'Paul', 'Cyclisme', 1995, 55000, 6000),
     ('Petit', 'David', '22 Rue Lafayette', 'davidP22', 'Sophie', 'Cuisine', 1988, 48000, 4000),
     ('Martin', 'Emma', '11 Place de la République', 'emmaM11', 'Alice', 'Voyage', 1992, 62000, 8000);
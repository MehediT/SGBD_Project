DROP TABLE IF EXISTS programmeur;
CREATE TABLE programmeur (
     id SERIAL PRIMARY KEY,        -- ID unique auto-incrémenté
     nom VARCHAR(100) NOT NULL,    -- Nom du programmeur
     prenom VARCHAR(100) NOT NULL, -- Prénom du programmeur
     adresse VARCHAR(255),         -- Adresse
     pseudo VARCHAR(50) NOT NULL,  -- Pseudo unique
     responsable INT DEFAULT -1,   -- Responsable (valeur par défaut : -1)
     hobby VARCHAR(50),            -- Hobby du programmeur
     annNaissance INT,             -- Année de naissance
     salaire INT,                  -- Salaire annuel
     prime INT DEFAULT 0           -- Prime annuelle
);


INSERT INTO programmeur
(nom, prenom, adresse, pseudo, hobby, annNaissance, salaire, prime)
VALUES
    ('Dupont', 'Alice', '123 Rue de Paris', 'alice123', 'Lecture', 1990, 50000, 5000),
    ('Durand', 'Bob', '45 Avenue des Champs', 'bobD45', 'Musique', 1985, 60000, 7000),
    ('Moreau', 'Charlie', '78 Boulevard Haussmann', 'charlieM', 'Cyclisme', 1995, 55000, 6000);
INSERT INTO programmeur
(nom, prenom, adresse, pseudo, responsable, hobby, annNaissance, salaire, prime)
VALUES
    ('Petit', 'David', '22 Rue Lafayette', 'davidP22', 1,'Cuisine', 1988, 48000, 4000),
    ('Martin', 'Emma', '11 Place de la République', 'emmaM11', 3,'Voyage', 1992, 62000, 8000);

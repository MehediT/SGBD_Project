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
     salaire FLOAT,                -- Salaire annuel
     prime FLOAT DEFAULT 0         -- Prime annuelle
);


INSERT INTO programmeur
(nom, prenom, adresse, pseudo, hobby, annNaissance, salaire, prime)
VALUES
    ('Dupont', 'Alice', '123 Rue de Paris', 'alice123', 'Lecture', 1990, 52200.12, 540.00),
    ('Durand', 'Bob', '45 Avenue des Champs', 'bobD45', 'Musique', 1985, 60800.12, 78.00),
    ('Moreau', 'Charlie', '78 Boulevard Haussmann', 'charlieM', 'Cyclisme', 1995, 5500.19, 6000.58);
INSERT INTO programmeur
(nom, prenom, adresse, pseudo, responsable, hobby, annNaissance, salaire, prime)
VALUES
    ('Petit', 'David', '22 Rue Lafayette', 'davidP22', 1,'Cuisine', 1988, 48000.0, 4000.0),
    ('Martin', 'Emma', '11 Place de la République', 'emmaM11', 3,'Voyage', 1992, 62000.0, 8000.0);

package org.example.model;

/**
 * La classe Programmeur représente un programmeur avec ses informations personnelles,
 * professionnelles et financières.
 * </br>
 * Elle contient des informations telles que l'identifiant, le nom, le prénom,
 * l'adresse, le pseudo, le responsable, les hobbies, l'année de naissance,
 * le salaire et la prime.
 *
 * @author Mehedi Touré & Adil Chetouni
 * @version 1.0
 * @since   1.0
 */
public class Programmeur {

    /** Identifiant unique du programmeur */
    private int id;

    /** Nom du programmeur */
    private String nom;

    /** Prénom du programmeur */
    private String prenom;

    /** Adresse du programmeur */
    private String adresse;

    /** Pseudo du programmeur */
    private String pseudo;

    /** Responsable du programmeur */
    private int responsable;

    /** Hobby ou centre d'intérêt du programmeur */
    private String hobby;

    /** Année de naissance du programmeur */
    private int anNaissance;

    /** Salaire du programmeur */
    private float salaire;

    /** Prime associée au programmeur */
    private float prime;

    /**
     * Constructeur avec tous les paramètres.
     *
     * @param id          Identifiant unique
     * @param nom         Nom du programmeur
     * @param prenom      Prénom du programmeur
     * @param adresse     Adresse du programmeur
     * @param pseudo      Pseudo du programmeur
     * @param responsable Responsable du programmeur
     * @param hobby       Hobby ou centre d'intérêt
     * @param annNaissance Année de naissance
     * @param salaire     Salaire du programmeur
     * @param prime       Prime associée
     */
    public Programmeur(int id, String nom, String prenom, String adresse, String pseudo, int responsable, String hobby, int annNaissance, float salaire, float prime) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.pseudo = pseudo;
        this.responsable = responsable;
        this.hobby = hobby;
        this.anNaissance = annNaissance;
        this.salaire = salaire;
        this.prime = prime;
    }

    /**
     * Constructeur sans identifiant (valeur par défaut 0).
     *
     * @param nom         Nom du programmeur
     * @param prenom      Prénom du programmeur
     * @param adresse     Adresse du programmeur
     * @param pseudo      Pseudo du programmeur
     * @param responsable Responsable du programmeur
     * @param hobby       Hobby ou centre d'intérêt
     * @param annNaissance Année de naissance
     * @param salaire     Salaire du programmeur
     * @param prime       Prime associée
     */
    public Programmeur(String nom, String prenom, String adresse, String pseudo, int responsable, String hobby, int annNaissance, int salaire, int prime) {
        this(0, nom, prenom, adresse, pseudo, responsable, hobby, annNaissance, salaire, prime);
    }

    /**
     * Constructeur par défaut initialisant les champs avec des valeurs par défaut.
     */
    public Programmeur() {
        this(0, "", "", "", "", 0, "", 0, 0, 0);
    }

    /**
     * Obtient l'identifiant du programmeur.
     *
     * @return L'identifiant
     */
    public int getId() {
        return id;
    }

    /**
     * Définit l'identifiant du programmeur.
     *
     * @param id L'identifiant à définir
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtient le nom du programmeur.
     *
     * @return Le nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Définit le nom du programmeur.
     *
     * @param nom Le nom à définir
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Obtient le prénom du programmeur.
     *
     * @return Le prénom
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Définit le prénom du programmeur.
     *
     * @param prenom Le prénom à définir
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * Obtient l'adresse du programmeur.
     *
     * @return L'adresse
     */
    public String getAdresse() {
        return adresse;
    }

    /**
     * Définit l'adresse du programmeur.
     *
     * @param adresse L'adresse à définir
     */
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    /**
     * Obtient le pseudo du programmeur.
     *
     * @return Le pseudo
     */
    public String getPseudo() {
        return pseudo;
    }

    /**
     * Définit le pseudo du programmeur.
     *
     * @param pseudo Le pseudo à définir
     */
    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    /**
     * Obtient le responsable du programmeur.
     *
     * @return Le responsable
     */
    public int getResponsable() {
        return responsable;
    }

    /**
     * Définit le responsable du programmeur.
     *
     * @param responsable Id responsable à définir
     */
    public void setResponsable(int responsable) {
        this.responsable = responsable;
    }

    /**
     * Obtient le hobby du programmeur.
     *
     * @return Le hobby
     */
    public String getHobby() {
        return hobby;
    }

    /**
     * Définit le hobby du programmeur.
     *
     * @param hobby Le hobby à définir
     */
    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    /**
     * Obtient l'année de naissance du programmeur.
     *
     * @return L'année de naissance
     */
    public int getAnNaissance() {
        return anNaissance;
    }

    /**
     * Définit l'année de naissance du programmeur.
     *
     * @param anNaissance L'année de naissance à définir
     */
    public void setAnNaissance(int anNaissance) {
        this.anNaissance = anNaissance;
    }

    /**
     * Obtient le salaire du programmeur.
     *
     * @return Le salaire
     */
    public float getSalaire() {
        return salaire;
    }

    /**
     * Définit le salaire du programmeur.
     *
     * @param salaire Le salaire à définir
     */
    public void setSalaire(float salaire) {
        this.salaire = salaire;
    }

    /**
     * Obtient la prime associée au programmeur.
     *
     * @return La prime
     */
    public float getPrime() {
        return prime;
    }

    /**
     * Définit la prime associée au programmeur.
     *
     * @param prime La prime à définir
     */
    public void setPrime(float prime) {
        this.prime = prime;
    }

    /**
     * Retourne une représentation textuelle des informations du programmeur.
     *
     * @return Une chaîne de caractères contenant toutes les informations
     */
    @Override
    public String toString() {
        return  "Id\t\t\t\t: " + id +
                "\nNom\t\t\t\t: " + nom +
                "\nPrenom\t\t\t: " + prenom +
                "\nAdresse\t\t\t: " + adresse +
                "\nPseudo\t\t\t: " + pseudo +
                "\nResponsable\t\t: " + responsable +
                "\nHobby\t\t\t: " + hobby +
                "\nNaissance\t\t: " + anNaissance +
                "\nSalaire\t\t\t: " + salaire +
                "\nPrime\t\t\t: " + prime +
                "\n---------------------------";
    }
}

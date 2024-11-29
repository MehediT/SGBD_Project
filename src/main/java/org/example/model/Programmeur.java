package org.example.model;

public class Programmeur {

    private int id;
    private String nom;
    private String prenom;
    private String adresse;
    private String pseudo;
    private String responsable;
    private String hobby;

    private int anNaissance;
    private int salaire;
    private int prime;

    public Programmeur(int id, String nom, String prenom, String adresse, String pseudo, String responsable, String hobby, int annNaissance, int salaire, int prime) {
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
    public Programmeur(String nom, String prenom, String adresse, String pseudo, String responsable, String hobby, int annNaissance, int salaire, int prime) {
        this(0, nom, prenom, adresse, pseudo, responsable, hobby, annNaissance, salaire, prime);
    }
    public Programmeur(){
        this(0, "", "", "", "", "", "", 0, 0, 0);
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public int getAnNaissance() {
        return anNaissance;
    }

    public void setAnNaissance(int anNaissance) {
        this.anNaissance = anNaissance;
    }

    public int getSalaire() {
        return salaire;
    }

    public void setSalaire(int salaire) {
        this.salaire = salaire;
    }

    public int getPrime() {
        return prime;
    }

    public void setPrime(int prime) {
        this.prime = prime;
    }

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
                "\n---------------------------"
                ;
    }
}

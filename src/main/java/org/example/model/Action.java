package org.example.model;

import java.util.Objects;

public class Action{

    private String action;
    private String date;
    private Programmeur programmeur;

    public Action(String action, String date, Programmeur programmeur){
        this.action = action;
        this.date = date;
        this.programmeur = new Programmeur(
                programmeur.getId(),
                programmeur.getNom(),
                programmeur.getPrenom(),
                programmeur.getAdresse(),
                programmeur.getPseudo(),
                programmeur.getResponsable(),
                programmeur.getHobby(),
                programmeur.getAnNaissance(),
                programmeur.getSalaire(),
                programmeur.getPrime()
        );
    }


    public Action(String action, String date){
        this(action,date, null);
    }
    public String getActionName(){
        return action;
    }

    public String getActionDate(){
        return date;
    }

    public String getDetails(){
        if (programmeur == null){
            return "";
        }
        String details = "Nom: " + programmeur.getNom() + " ";
        details += "Prenom: " + programmeur.getPrenom() + " ";
        if(!Objects.equals(programmeur.getResponsable(), "")){
            details += "Responsable: " + programmeur.getResponsable();
        }
        return details;
    }

    public void setAction(String action){
        this.action = action;
    }

    public void setDate(String date){
        this.date = date;
    }

    public Programmeur getProgrammeur(){
        return programmeur;
    }

    public void setProgrammeur(Programmeur Programmeur){
        this.programmeur = Programmeur;
    }

    public String toString(){
        return "Action: " + action + " Date: " + date + (programmeur != null ? "\nPour le programmeur: " + programmeur : "");
    }
}

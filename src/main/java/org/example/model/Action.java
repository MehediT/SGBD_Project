package org.example.model;

public class Action{

    private String action;
    private String date;
    private Programmeur Programmeur;

    public Action(String action, String date, Programmeur programmeur){
        this.action = action;
        this.date = date;
    }
    public Action(String action, String date){
        this(action,date, null);
    }
    public String getAction(){
        return action;
    }

    public String getDate(){
        return date;
    }

    public void setAction(String action){
        this.action = action;
    }

    public void setDate(String date){
        this.date = date;
    }

    public Programmeur getProgrammeur(){
        return Programmeur;
    }

    public void setProgrammeur(Programmeur Programmeur){
        this.Programmeur = Programmeur;
    }

    public String toString(){
        return "Action: " + action + " Date: " + date + (Programmeur != null ? "\nPour le programmeur: " + Programmeur : "");
    }
}

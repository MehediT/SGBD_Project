package org.example.model;

import java.util.ArrayList;

public class Historique {

    ArrayList<Action> historique = new ArrayList<Action>();

    public void ajouterAction(Action action){
        historique.add(action);
    }

    public ArrayList<Action> getHistorique() {
        return historique;
    }

    public void showHistorique(){
        for (Action action : historique) {
            System.out.println(action);
        }
    }

    public void clearHistorique(){
        historique.clear();
    }
}


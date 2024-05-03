package org.insa.graphs.gui.simple;
import org.insa.graphs.model.Node;

public class Scenarios {
    String carte;
    String natureCout;
    Node origine;
    Node destination;
    

    public Scenarios(String carte, String natureCout,Node origine,Node destination){
        this.carte =carte;
        this.natureCout=natureCout;
        this.origine=origine;
        this.destination=destination;
    }

    public String getCarte() {
        return carte;
    }
    public String getNatureCout() {
        return natureCout;
    }
    public Node getOrigine() {
        return origine;
    }
    public Node getDestination() {
        return destination;
    }
    
    
}


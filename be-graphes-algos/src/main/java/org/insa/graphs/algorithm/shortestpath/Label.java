package org.insa.graphs.algorithm.shortestpath;

import org.insa.graphs.model.Arc;
import org.insa.graphs.model.Node;

public class Label implements Comparable<Label> {
    
    private Node sommetCourant;
    private Boolean marked;
    private double costRealised;
    private Arc pere;


    public Label(Node sommetCourant){
        this.sommetCourant= sommetCourant;
        this.marked= false;
        this.costRealised= Double.MAX_VALUE;
        this.pere= null;
    }

    public Node getSommetCourant() {
        return sommetCourant;
    }
    public Boolean getMarked() {
        return marked;
    }

    public Arc getPere() {
        return pere;
    }
    
    public double getCost() {
        return costRealised;
    }

    public void setSommetCourant(Node sommetCourant) {
        this.sommetCourant = sommetCourant;
    }

    public void setMarked(Boolean marked) {
        this.marked = marked;
    }

    public void setCost(double costRealised) {
        this.costRealised = costRealised;
    }

    public void setPere(Arc pere) {
        this.pere = pere;
    }

    @Override
    public int compareTo(Label toCompare) {
        return Double.compare(this.getTotalCost(), toCompare.getTotalCost());
    }
    
    public double getTotalCost(){
        return this.getCost();
    }
    
    
}

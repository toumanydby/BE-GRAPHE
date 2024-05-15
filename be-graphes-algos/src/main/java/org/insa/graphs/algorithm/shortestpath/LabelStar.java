package org.insa.graphs.algorithm.shortestpath;

import org.insa.graphs.model.Node;

public class LabelStar extends Label implements Comparable<Label> {
    
    private double coutDestination;

    public LabelStar(Node sommetCourant, double coutDestination){
        super(sommetCourant);
        this.coutDestination = coutDestination;
    }

    public double getTotalCost(){
        return this.getCost() + this.coutDestination;
    }
    
}
package org.insa.graphs.algorithm.shortestpath;

import org.insa.graphs.model.Node;


public class LabelOchlo extends LabelStar {

    private int degSommet;
    private int degMax;
    private int paramPonderation;
    

    public LabelOchlo(Node sommetCourant, double coutDestination, int degSommet, int degMax, int paramPonderation){
        super(sommetCourant,coutDestination);
        this.degSommet = degSommet;
        this.degMax = degMax;
        this.paramPonderation = paramPonderation;
    }

    
    public double getTotalCost(){
        return super.getTotalCost() + (this.getParamPonderation() * (this.getDegSommet() / this.getDegMax()));
    }

    public int getDegSommet() {
        return degSommet;
    }

    public int getDegMax() {
        return degMax;
    }

    public int getParamPonderation() {
        return paramPonderation;
    }

}

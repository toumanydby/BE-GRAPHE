package org.insa.graphs.algorithm.shortestpath;

import java.io.ObjectInputFilter.Status;

import org.insa.graphs.algorithm.utils.BinaryHeap;
import org.insa.graphs.algorithm.utils.ElementNotFoundException;
import org.insa.graphs.model.Arc;
import org.insa.graphs.model.Graph;
import org.insa.graphs.model.Node;
import org.insa.graphs.model.Path;

public class DijkstraAlgorithm extends ShortestPathAlgorithm {

    protected void initLabel( Label[] labelTab, ShortestPathData data){
        for (Node node : data.getGraph().getNodes()) {
            labelTab[node.getId()] = new Label(node);
        }
    }

    public DijkstraAlgorithm(ShortestPathData data) {
        super(data);
    }

    @Override
    protected ShortestPathSolution doRun() {
        final ShortestPathData data = getInputData();
        ShortestPathSolution solution = null;
        
        // TODO:

        Graph ourGraph = data.getGraph();
        final int nbNodes = ourGraph.size();

        Label [] labels = new Label[nbNodes];

        initLabel(labels, data);

        BinaryHeap<Label> tasBinaire = new BinaryHeap<Label>();

        labels[data.getOrigin().getId()].setCost(0);;
        tasBinaire.insert(labels[data.getOrigin().getId()]);

        Label x;

        while ( !(tasBinaire.isEmpty()) 
                && !(labels[data.getDestination().getId()].getMarked())) {
            
            x = tasBinaire.deleteMin();
            x.setMarked(true);
            
            for (Arc arc : x.getSommetCourant().getSuccessors()) {
                Node succDesti = arc.getDestination();
                if ( !(labels[succDesti.getId()].getMarked()) ) {
                    if (
                        (labels[succDesti.getId()].getCost() > labels[x.getSommetCourant().getId()].getCost() + data.getCost(arc)  
                        && data.isAllowed(arc))
                    ) {
                        try {
                            tasBinaire.remove(labels[succDesti.getId()]);
                        } catch (ElementNotFoundException e) {}

                        labels[succDesti.getId()].setPere(arc);
                        labels[succDesti.getId()].setCost(labels[x.getSommetCourant().getId()].getCost()  + data.getCost(arc));
                        labels[succDesti.getId()] = new Label(succDesti);
                    }
                }
            }
        }


        if ((labels[data.getDestination().getId()].getMarked())) {
            Node[] listPereSucc;

            for (int i = 0; i < labels.length ; i++){
                listPereSucc[i] = labels[]
            }

            Path finalPath = new Path(ourGraph, listPereSucc);
            solution = new ShortestPathSolution(data, Status.ALLOWED, finalPath);
        } else{

        }
        return solution;
    }

}

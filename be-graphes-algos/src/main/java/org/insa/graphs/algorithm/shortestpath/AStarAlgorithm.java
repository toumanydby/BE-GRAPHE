package org.insa.graphs.algorithm.shortestpath;

import org.insa.graphs.model.Node;


/**
 * Fournir forcement le plus court chemin
 */
public class AStarAlgorithm extends DijkstraAlgorithm {
    

    @Override
    protected void initLabel(Label[] labelTab, ShortestPathData data) {
        for (Node node : data.getGraph().getNodes()) {
            labelTab[node.getId()] = new LabelStar(node,node.getPoint().distanceTo(data.getDestination().getPoint()));
        }
    }

    public AStarAlgorithm(ShortestPathData data) {
        super(data);
    }


}

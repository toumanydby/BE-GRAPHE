package org.insa.graphs.algorithm.shortestpath;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.insa.graphs.model.Node;

/**
 * Objectif: On souhaite se rendre d'un point A a un point B, un usager (piéton,
 * cycliste, automobiliste : à vous de choisir)
 * souhaite trouver un trajet évitant les lieux (sommets) potentiellement les
 * plus fréquentés.
 * On a un parametre de tolerance, ce chemin ne doit pas etre trop long par
 * rapport au PCC
 * Parametre de tolerance choisi: Coefficient du rapport entre PCC de
 * OchlophobeAlgorithm et PCC de DijkstraAlgorithm
 * Methode pour identifier les lieux potentiellement les plus frequentes ( Ceux
 * pertinents pour aller de A vers B ).
 * Nous caracterisons la notion de lieux frequentes par des careffours: Plus
 * explicitement les sommets ayant les plus d'arcs
 * entrants ou sortants. Nous prevoyons de compter ces ars et les sommets ayant
 * un nombre au dela d'un certain seuil seront
 * consideres comme lieux potentiellement les plus frequentes
 * 
 * PS: Pour des raisons d'efficacité vous pouvez réduire la zone géographique
 * considérée (en fonction des deux points donnés
 * par l'usager et du paramètre de tolérance).
 * 
 * On doit trouver une methode pour determiner le tajet de cet usager.
 * 
 * PROCEDURE TO DO FOR THE ALGORITHM:
 * 1. trouver les sommets les plus frequentes allant de A vers B
 * 2. Determiner le PCC chemin avec Dijkstra de A vers B
 * 3. Les marques déja dans leur label de facon a ce que l'algo de Dijkstra ne
 * les considere pas.
 * 4. Determiner le PCC de A vers B avec OchlophobeAlgorithm
 * 5. Comparer en fonction du critere de tolerance si acceptable ou pas.
 * 6. Sinon ...........
 * 
 * 
 * 
 * MAP: Toulouse 
 * values to test: 
 *  1726 8907
 * 
 * BORDEAUX 
 * 4737 5491
 *  
 * 
 * Proposez un algorithme sans l'implémenter (sauf si vous êtes motivés).
Essayez de démontrer que votre algorithme donne une solution correcte, bonus : démontrer que la solution est optimale.
Calculez la complexité de votre algo.

 */

public class OchlophobeAlgorithm extends DijkstraAlgorithm {

    @Override
    protected void initLabel(Label[] labelTab, ShortestPathData data) {
        int degMax = 0;
        for (Node node : data.getGraph().getNodes()) {
            if (node.getNumberOfSuccessors() > degMax) {
                degMax = node.getNumberOfSuccessors();
            }
        }

        for (Node node : data.getGraph().getNodes()) {
            labelTab[node.getId()] = new LabelOchlo(node,node.getPoint().distanceTo(data.getDestination().getPoint()),node.getNumberOfSuccessors(),degMax,50);
        }
    }

    /*
    @Override
    protected void initLabel(Label[] labelTab, ShortestPathData data) {
        //ArrayList<Integer> numberOfSuccessors = new ArrayList<Integer>();
        Map<Integer,Integer> mapIDNumberSuccessors = new HashMap<Integer,Integer>();

        for (Node node : data.getGraph().getNodes()) {
            //numberOfSuccessors.add(node.getNumberOfSuccessors());
            mapIDNumberSuccessors.put(node.getId(), node.getNumberOfSuccessors());
        }

        Map<Integer, Integer> sortedByValue = mapIDNumberSuccessors.entrySet()
            .stream()
            .sorted(Map.Entry.comparingByValue())
            .collect(Collectors.toMap(
                Map.Entry::getKey, 
                Map.Entry::getValue, 
                (oldValue, newValue) -> oldValue, LinkedHashMap::new));

        
        //for (Map.Entry<Integer, Integer> entry : sortedByValue.entrySet()) {
        //    int key = entry.getKey();
        //    int value = entry.getValue();
        //    System.out.print("Key: " + key + ", Value: " + value + " --- ");
        //}
         

        int l = Math.round((sortedByValue.size() * 3) / 4);

        int cpt = 0;
        for (Map.Entry<Integer, Integer> entry : sortedByValue.entrySet()) {
            cpt++;
            int key = entry.getKey();
            Node node = data.getGraph().get(key);
            
            if (node == data.getDestination() || node == data.getOrigin()) {
                labelTab[node.getId()] = new LabelStar(node,
                        node.getPoint().distanceTo(data.getDestination().getPoint()));
                continue;
            }

            labelTab[node.getId()] = new LabelStar(node, node.getPoint().distanceTo(data.getDestination().getPoint()));
            if (cpt >= l) {
                if (!(node == data.getDestination() || node == data.getOrigin())){
                    labelTab[node.getId()].setMarked(true);
                }
            }
        }
    }
*/
    public OchlophobeAlgorithm(ShortestPathData data) {
        super(data);
        
    }

}

package org.insa.graphs.algorithm.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;

import org.insa.graphs.model.io.BinaryGraphReader;
import org.insa.graphs.model.io.GraphReader;
import org.insa.graphs.model.Graph;

import org.insa.graphs.algorithm.shortestpath.*;
import org.insa.graphs.algorithm.ArcInspectorFactory;

public class AlgosPerfAndOptiTest {

    // Graph utilisé pour les tests
    static Graph graph;
    
    //Lecture du graphe
    @BeforeClass
    public static void initAll() throws Exception {
    	final String mapName = "/home/toumany-doumbouya/Documents/cours/insa/3a/s2/graphes/BE-GRAPHE/Maps/haute-garonne.mapgr";

        // Create a graph reader.
        final GraphReader reader = new BinaryGraphReader(
                new DataInputStream(new BufferedInputStream(new FileInputStream(mapName))));

        // Read the graph.
        AlgosPerfAndOptiTest.graph = reader.read();

        reader.close();
    }
    
@Test
    public void testCheminNul() {
    	// On ne fait le test qu'avec un filtre sur les chemins autorisés
    	ShortestPathData data = new ShortestPathData(graph, graph.get(12305), graph.get(12305), ArcInspectorFactory.getAllFilters().get(0));
    	ShortestPathAlgorithm dijkstra = new DijkstraAlgorithm(data);
    	ShortestPathAlgorithm aStar = new AStarAlgorithm(data);
        ShortestPathSolution solutionAStar = aStar.run();
    	ShortestPathSolution solutionDijkstra = dijkstra.run();

        assertTrue(solutionDijkstra.isFeasible());
        assertTrue(solutionAStar.isFeasible());
        assertTrue(solutionDijkstra.getPath().isEmpty());
        assertTrue(solutionAStar.getPath().isEmpty());
    }
    
        @Test
    public void testAucunCheminPossible() {
    	// On ne fait le test qu'avec un filtre sur les chemins autorisés
    	ShortestPathData data = new ShortestPathData(graph, graph.get(137947), graph.get(75870), ArcInspectorFactory.getAllFilters().get(0));
    	ShortestPathAlgorithm dijkstra = new DijkstraAlgorithm(data);
    	ShortestPathAlgorithm aStar = new AStarAlgorithm(data);
    	ShortestPathSolution solutionAStar = aStar.run();
    	ShortestPathSolution solutionDijkstra = dijkstra.run();
        assertFalse(solutionDijkstra.isFeasible());
        assertFalse(solutionAStar.isFeasible());
    }    
    
    @Test
    public void testOptiLengthAllRoadAllowed() {
    	ShortestPathData data = new ShortestPathData(graph, graph.get(92173), graph.get(75870), ArcInspectorFactory.getAllFilters().get(0));
    	ShortestPathAlgorithm bellmanFord = new BellmanFordAlgorithm(data);
    	ShortestPathAlgorithm dijkstra = new DijkstraAlgorithm(data);
    	ShortestPathAlgorithm aStar = new AStarAlgorithm(data);
    	ShortestPathSolution solutionAStar = aStar.run();
    	ShortestPathSolution solutionDijkstra = dijkstra.run();
    	ShortestPathSolution solutionBellmanFord = bellmanFord.run();
        assertEquals(solutionBellmanFord.getPath().getLength(),solutionDijkstra.getPath().getLength(), 0.00001);
        assertEquals(solutionBellmanFord.getPath().getLength(),solutionAStar.getPath().getLength(), 0.00001);
    }
    
    @Test
    public void testOptiLengthOnlyRoadOpenForCarsAllowed() {
    	ShortestPathData data = new ShortestPathData(graph, graph.get(92173), graph.get(75870), ArcInspectorFactory.getAllFilters().get(1));
    	ShortestPathAlgorithm bellmanFord = new BellmanFordAlgorithm(data);
    	ShortestPathAlgorithm dijkstra = new DijkstraAlgorithm(data);
    	ShortestPathAlgorithm aStar = new AStarAlgorithm(data);
    	ShortestPathSolution solutionAStar = aStar.run();
    	ShortestPathSolution solutionDijkstra = dijkstra.run();
    	ShortestPathSolution solutionBellmanFord = bellmanFord.run();
        assertEquals(solutionBellmanFord.getPath().getLength(),solutionDijkstra.getPath().getLength(), 0.00001);
        assertEquals(solutionBellmanFord.getPath().getLength(),solutionAStar.getPath().getLength(), 0.00001);
    }
    
    @Test
    public void testOptiTimeOnlyRoadOpenForCarsAllowed() {
    	ShortestPathData data = new ShortestPathData(graph, graph.get(92173), graph.get(75870), ArcInspectorFactory.getAllFilters().get(2));
    	ShortestPathAlgorithm bellmanFord = new BellmanFordAlgorithm(data);
    	ShortestPathAlgorithm dijkstra = new DijkstraAlgorithm(data);
    	ShortestPathAlgorithm aStar = new AStarAlgorithm(data);
    	ShortestPathSolution solutionAStar = aStar.run();
    	ShortestPathSolution solutionDijkstra = dijkstra.run();
    	ShortestPathSolution solutionBellmanFord = bellmanFord.run();
        assertEquals(solutionBellmanFord.getPath().getLength(),solutionDijkstra.getPath().getLength(), 0.00001);
        assertTrue(solutionDijkstra.getPath().getLength() < solutionAStar.getPath().getLength());
        //assertEquals(solutionDijkstra.getPath().getLength(),solutionAStar.getPath().getLength(), 0.00001);
    }
    
    @Test
    public void testOptiTimePedestriansAndBicyclesAllowed() {
    	ShortestPathData data = new ShortestPathData(graph, graph.get(92173), graph.get(75870), ArcInspectorFactory.getAllFilters().get(3));
    	ShortestPathAlgorithm bellmanFord = new BellmanFordAlgorithm(data);
    	ShortestPathAlgorithm dijkstra = new DijkstraAlgorithm(data);
    	ShortestPathAlgorithm aStar = new AStarAlgorithm(data);
    	ShortestPathSolution solutionAStar = aStar.run();
    	ShortestPathSolution solutionDijkstra = dijkstra.run();
    	ShortestPathSolution solutionBellmanFord = bellmanFord.run();
        assertEquals(solutionBellmanFord.getPath().getLength(),solutionDijkstra.getPath().getLength(), 0.00001);
        assertTrue(solutionDijkstra.getPath().getLength() < solutionAStar.getPath().getLength());
        //assertEquals(solutionDijkstra.getPath().getLength(),solutionAStar.getPath().getLength(), 0.00001);
    }
    
}


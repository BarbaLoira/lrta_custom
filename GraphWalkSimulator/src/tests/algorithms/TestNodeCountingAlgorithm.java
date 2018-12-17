package tests.algorithms;

import java.io.IOException;

import algorithms.nodecounting.NodeCountingAlgorithm;
import yaps.graph.Graph;
import yaps.graph.GraphFileFormat;
import yaps.graph.GraphFileUtil;
import yaps.patrolling_metrics.IntervalMetricsReport;
import yaps.patrolling_metrics.VisitsList;
import yaps.simulator.core.Simulator;


public class TestNodeCountingAlgorithm {

	public static void main(String[] args) throws IOException {
		Graph graph = GraphFileUtil.read("maps\\map_a.xml", GraphFileFormat.SIMPATROL); 
		int[] agentsInitialPositions = new int[]{ 5, 10 }; //2 agents
		int totalTime = 1000;
		
		//System.out.println(graph);

		Simulator simulator = new Simulator();
		
		NodeCountingAlgorithm algorithm = new NodeCountingAlgorithm();

		simulator.setGraph(graph);
		simulator.setAgentsInitialNodes(agentsInitialPositions);
		simulator.setTotalTime(totalTime);
		simulator.setAlgorithm(algorithm);

		System.out.println("ALGORITHM: " + algorithm.getName() + "\n");
		simulator.run();
		
		showPatrollingMetrics(simulator.getVisitsList(), graph.getNumNodes(), totalTime);
	}

	private static void showPatrollingMetrics(VisitsList visits, int nodes, int time) {
		System.out.println();
		System.out.println(visits);
		
		IntervalMetricsReport intervalReport = new IntervalMetricsReport(nodes, 1, time, visits);
		
		System.out.println("Metricas:");
		System.out.printf(" - desvio padrao dos intervalos: %.3f \n", intervalReport.getStdDevOfIntervals());
		System.out.printf(" - intervalo quadratico medio: %.3f \n", intervalReport.getQuadraticMeanOfIntervals());
		System.out.printf(" - intervalo maximo: %.3f \n", intervalReport.getMaximumInterval());

	}


}

package tests.algorithms;

import java.io.IOException;

import algorithms.lrta.LrtaAlgorithm;
import algorithms.lrtaBacktracking.BacktrackingAlgorithm;
import algorithms.lrtaDeeperLookahead.DeeperLookaheadAlgorithm;
import yaps.graph.Graph;
import yaps.graph.GraphFileFormat;
import yaps.graph.GraphFileUtil;
import yaps.simulator.core.Algorithm;
import yaps.simulator.core.Simulator;

public class TestMainAlgorithm {
	static Graph graph;
	static Simulator simulator;

	static int totalTime = 1000;

	static int meta = 15;
	static int rodadas = 10;
	static int[] agentsInitialPositions = new int[] { 5, 10 }; // 2 agents

	public static void main(String[] args) throws IOException {
		graph = GraphFileUtil.read("maps\\map_grid.xml", GraphFileFormat.SIMPATROL);

//		Algorithm algorithm = new LrtaAlgorithm(meta);
//		algorithmRun(algorithm);

//		Algorithm  algorithm = new BacktrackingAlgorithm(meta);
//	 	algorithmRun(algorithm); 

		int profundidadeMax = 2;
		Algorithm algorithm = new DeeperLookaheadAlgorithm(meta, profundidadeMax);
		algorithmRun(algorithm);
	}

	private static void algorithmRun(Algorithm algorithm) {
		System.out.println("Algoritmo  " + algorithm.getClass().getSimpleName().toString());
		int rodadaAtual = 0;
		while (rodadas != rodadaAtual) {

			System.out.println("\nRodada ------- " + (rodadaAtual + 1) + "\n");

			simulator = new Simulator();

			simulator.setGraph(graph);
			simulator.setAgentsInitialNodes(agentsInitialPositions);
			simulator.setTotalTime(totalTime);
			simulator.setAlgorithm(algorithm);

			simulator.run();

			rodadaAtual += 1;
		}
	}

}

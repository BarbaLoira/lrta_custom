package algorithms.lrta;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import algorithms.lrta.Util;
import yaps.graph.Graph;
import yaps.simulator.multiagent.SimpleAgent;
import yaps.util.RandomUtil;

public class LrtaAgent extends SimpleAgent {
	private Graph graph;
	private int[] visitsOfNode;
	private int t = 0;
	public int executionConvergenceCost = 0;
	public float planingCost = 0;
	public List<Heuristica> heuristicas;

	public LrtaAgent(Graph g, int[] memory, int meta) {
		super(System.out);
		this.graph = g;
		Util.graph = this.graph;
		this.visitsOfNode = memory;
		Util.meta = meta;
	}

	@Override
	public void onStart() {

	}

	@Override
	public void onTurn(int nextTurn) {
		// nop

	}

	@Override
	public int onArrivalInNode(int nextTurn) {
		Util.startFirstMove();

		t = position.getCurrentNode();

		int proximoVizinho = 0;

		Util.verificarCriacaoHeuristica(t);// verifica se necessita criar heuristica caso sim a heuristica inicia com
											// valor zero

		List<Integer> neighbors = graph.getSuccessors(t);
		// System.out.println(neighbors);
		if (t != Util.meta) {

			proximoVizinho = Util.getVizinhoMenorCustoHeuristica(t, neighbors); // st+1 ← arg min (c(st, s) + ht(s))

			Util.setMaiorHeuristicaParaNodeAtual(t, proximoVizinho); // ht+1(st) ← max{ ht(st), min(c(st, s) + ht(s)}

		}

		performances(neighbors, proximoVizinho);
		heuristicas = Util.heuristicas;
		return proximoVizinho;
	}

	private void performances(List<Integer> neighbors, int proximoVizinho) {
		executionConvergenceCost += Util.custoAteVizinho(t, proximoVizinho); // execution convergence cost
		Util.planningCost(neighbors);
		planingCost = Util.calcularTotalPlanningCost(); // planning cost
		Util.finishFirstMove(); // first-move delay
	}

}

package algorithms.lrta;

import java.util.List;

import algorithms.lrta.Util;
import yaps.graph.Graph;
import yaps.simulator.multiagent.SimpleAgent;

public class LrtaAgent extends SimpleAgent {
	private Graph graph;
	private int t = 0;
	public float executionConvergenceCost = 0;
	public float planingCost = 0;
	public float firstTime = 0;

	public LrtaAgent(Graph g, int meta) {
		super(System.out);
		this.graph = g;
		Util.graph = this.graph;
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
		int proximoVizinho = 0;
		t = position.getCurrentNode();
		if (t != Util.meta) {
			Util.startFirstMove();

			Util.verificarCriacaoHeuristica(t);// verifica se necessita criar heuristica caso sim a heuristica inicia
												// com
												// valor zero

			List<Integer> neighbors = graph.getSuccessors(t);
			// System.out.println(neighbors);

			proximoVizinho = Util.getVizinhoMenorCustoHeuristica(t, neighbors); // st+1 ← arg min (c(st, s) + ht(s))

			Util.setMaiorHeuristicaParaNodeAtual(t, proximoVizinho); // ht+1(st) ← max{ ht(st), min(c(st, s) + ht(s)}

			performances(neighbors, proximoVizinho);

			return proximoVizinho;
		} else {
			return -1;
		}

	}

	private void performances(List<Integer> neighbors, int proximoVizinho) {
		executionConvergenceCost += Util.custoAteVizinho(t, proximoVizinho); // execution convergence cost
		Util.planningCost(neighbors);
		planingCost = Util.calcularTotalPlanningCost(); // planning cost
		firstTime = Util.finishFirstMove(); // first-move delay
	}

}

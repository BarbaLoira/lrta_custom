package algorithms.lrtaBacktracking;

import java.util.ArrayList;
import java.util.List;

import algorithms.lrta.Util;
import yaps.graph.Graph;
import yaps.simulator.multiagent.SimpleAgent;

public class bacnTrackingAgent extends SimpleAgent {
	private Graph graph;
	private int t = 0;
	List<Integer> backtracking = new ArrayList<Integer>();

	public float executionConvergenceCost = 0;
	public float planingCost = 0;
	public float firstTime = 0;

	public bacnTrackingAgent(Graph g, int meta) {
		super(System.out);
		this.graph = g;
		UtilBackTracking.graph = this.graph;
		UtilBackTracking.meta = meta;

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

			UtilBackTracking.startFirstMove();

			UtilBackTracking.verificarCriacaoHeuristica(t);// verifica se necessita criar heuristica caso sim a
															// heuristica inicia com
			// valor zero

			List<Integer> neighbors = graph.getSuccessors(t);

			proximoVizinho = UtilBackTracking.getVizinhoMenorCustoHeuristica(t, neighbors);
			// ht+1(st) â†? max{

			if (UtilBackTracking.setMaiorHeuristicaParaNodeAtual(t, proximoVizinho) && backtracking.size() > 1) {
				proximoVizinho = backtracking.get(backtracking.size() - 1);
				backtracking.remove(backtracking.size() - 1);
			} else {
				backtracking.add(proximoVizinho);
			}

			performances(neighbors, proximoVizinho);
			return proximoVizinho;
		} else {
			return -1;
		}
	}

	private void performances(List<Integer> neighbors, int proximoVizinho) {
		executionConvergenceCost += UtilBackTracking.custoAteVizinho(t, proximoVizinho); // execution convergence cost
		UtilBackTracking.planningCost(neighbors);
		planingCost = UtilBackTracking.calcularTotalPlanningCost(); // planning cost
		firstTime = UtilBackTracking.finishFirstMove(); // first-move delay 
	}

}

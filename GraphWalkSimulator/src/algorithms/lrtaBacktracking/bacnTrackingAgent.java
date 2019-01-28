package algorithms.lrtaBacktracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import yaps.graph.Graph;
import yaps.simulator.multiagent.SimpleAgent;
import yaps.util.RandomUtil;

public class bacnTrackingAgent extends SimpleAgent {
	private Graph graph;
	private int[] visitsOfNode;
	private int t = 0;
	List<Integer> backtracking;

	public bacnTrackingAgent(Graph g, int[] memory, int meta) {
		super(System.out);
		this.graph = g;
		this.visitsOfNode = memory;
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
		t = position.getCurrentNode();
		int proximoVizinho = 0;

		UtilBackTracking.verificarCriacaoHeuristica(t);// verifica se necessita criar heuristica caso sim a heuristica inicia com
											// valor zero

		List<Integer> neighbors = graph.getSuccessors(t);

		if (t != UtilBackTracking.meta) {
			proximoVizinho = UtilBackTracking.getVizinhoMenorCustoHeuristica(t, neighbors);
			// ht+1(st) â†? max{
			if (UtilBackTracking.setMaiorHeuristicaParaNodeAtual(t, proximoVizinho) && backtracking.size() > 1) {
				proximoVizinho = backtracking.get(backtracking.size());
				backtracking.remove(backtracking.size());
			} else {
				backtracking.add(proximoVizinho);
			}
		}

		return proximoVizinho;
	}

}

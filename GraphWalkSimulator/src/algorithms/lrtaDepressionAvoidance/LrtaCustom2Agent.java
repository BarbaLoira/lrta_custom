package algorithms.lrtaDepressionAvoidance;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import algorithms.lrtaDepressionAvoidance.Util;
import yaps.graph.Graph;
import yaps.simulator.multiagent.SimpleAgent;
import yaps.util.RandomUtil;

public class LrtaCustom2Agent extends SimpleAgent {
	private Graph graph;
	private int[] visitsOfNode;
	private List<Integer> conjuntosNodeMinLearn;
	private int t = 0;
	private int firstNodeId = 0;
	private int limiarMinLearng = 1;

	public LrtaCustom2Agent(Graph g, int[] memory) {
		super(System.out);
		this.graph = g;
		this.visitsOfNode = memory;
	}

	@Override
	public void onStart() {
		Util.meta = position.getDestination();
	}

	@Override
	public void onTurn(int nextTurn) {
		// nop

	}

	@Override
	public int onArrivalInNode(int nextTurn) {
		t = position.getCurrentNode(); // t Ã‰ O NÃ“ ATUAL
		int proximoVizinho = 0;

		if (Util.heuristicas.size() == 0) {
			firstNodeId = t;
		}

		Util.verificarCriacaoHeuristica(t);// verifica se necessita criar heuristica caso sim a heuristica inicia com
											// valor zero

		 

		if (t != Util.meta && t != firstNodeId) {
			if(Util.calcularMinLearning(firstNodeId,t)>=limiarMinLearng)
			 conjuntosNodeMinLearn.add(t);
			
			proximoVizinho = Util.getVizinhoMenorCustoHeuristica(t, conjuntosNodeMinLearn); // st+1 â†? arg min (c(st, s) + ht(s))

			Util.setMaiorHeuristicaParaNodeAtual(t, proximoVizinho); // ht+1(st) â†? max{ ht(st), min(c(st, s) + ht(s)}


		}else {
			conjuntosNodeMinLearn.add(t);
		}
		// graph.getEdgeLength(proximoVizinho, Util.meta);
		// System.out.printf(" - next node: %d \n", nextNode);
		return proximoVizinho;
	}

}

package algorithms.lrtaDepressionAvoidance;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import algorithms.lrtaDepressionAvoidance.Util;
import yaps.graph.Graph;
import yaps.simulator.multiagent.SimpleAgent;
import yaps.util.RandomUtil;

public class DepressionAvoidanceAgent extends SimpleAgent {
	private Graph graph;
	private int[] visitsOfNode;
	private List<Integer> conjuntosNodeMinLearn;
	private int t = 0;
	private int firstNodeId = 0;
	private int limiarMinLearng = 1;
	
	 
	public int executionConvergenceCost = 0;
	public float planingCost = 0;

	public DepressionAvoidanceAgent(Graph g, int[] memory,int meta) {
		super(System.out);
		this.graph = g;
		this.visitsOfNode = memory; 
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
		t = position.getCurrentNode(); // t É O NÓ ATUAL
		int proximoVizinho = 0;

		if (Util.heuristicas.size() == 0) {
			firstNodeId = t;
		}

		Util.verificarCriacaoHeuristica(t);// verifica se necessita criar heuristica caso sim a heuristica inicia com
											// valor zero

		 

		if (t != Util.meta && t != firstNodeId) {
			if(Util.calcularMinLearning(firstNodeId,t)>=limiarMinLearng)
			 conjuntosNodeMinLearn.add(t);
			
			proximoVizinho = Util.getVizinhoMenorCustoHeuristica(t, conjuntosNodeMinLearn); // st+1 �? arg min (c(st, s) + ht(s))

			Util.setMaiorHeuristicaParaNodeAtual(t, proximoVizinho); // ht+1(st) �? max{ ht(st), min(c(st, s) + ht(s)}


		}else {
			conjuntosNodeMinLearn.add(t);
		}
		
		performances(conjuntosNodeMinLearn, proximoVizinho);
		// graph.getEdgeLength(proximoVizinho, Util.meta);
		// System.out.printf(" - next node: %d \n", nextNode);
		return proximoVizinho;
	}

	private void performances(List<Integer> neighbors, int proximoVizinho) {
		executionConvergenceCost += Util.custoAteVizinho(t, proximoVizinho); // execution convergence cost
		Util.planningCost(neighbors);
		planingCost = Util.calcularTotalPlanningCost(); // planning cost
		Util.finishFirstMove(); // first-move delay
	}

}

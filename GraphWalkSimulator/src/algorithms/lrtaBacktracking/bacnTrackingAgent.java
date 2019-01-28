package algorithms.lrtaBacktracking;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
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
	List<Integer> backtracking = new ArrayList<Integer>();
 
	public int executionConvergenceCost = 0;
	public float planingCost = 0;

	public bacnTrackingAgent(Graph g, int[] memory, int meta) {
		super(System.out);
		this.graph = g;
		UtilBackTracking.graph = this.graph;
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
		UtilBackTracking.startFirstMove();
		
		t = position.getCurrentNode();
		int proximoVizinho = 0;

		UtilBackTracking.verificarCriacaoHeuristica(t);// verifica se necessita criar heuristica caso sim a heuristica inicia com
											// valor zero

		List<Integer> neighbors = graph.getSuccessors(t);

		if (t != UtilBackTracking.meta) {
			proximoVizinho = UtilBackTracking.getVizinhoMenorCustoHeuristica(t, neighbors);
			// ht+1(st) â†? max{
		 
			if (UtilBackTracking.setMaiorHeuristicaParaNodeAtual(t, proximoVizinho) && backtracking.size() > 1) {
				proximoVizinho = backtracking.get(backtracking.size()-1);
				backtracking.remove(backtracking.size()-1);
			} else {
				backtracking.add(proximoVizinho);
			}
		}
		
		
		performances(neighbors, proximoVizinho);
		return proximoVizinho;
	}
	
	private void performances(List<Integer> neighbors, int proximoVizinho) {
		executionConvergenceCost += UtilBackTracking.custoAteVizinho(t, proximoVizinho); // execution convergence cost
		UtilBackTracking.planningCost(neighbors);
		planingCost = UtilBackTracking.calcularTotalPlanningCost(); // planning cost
		UtilBackTracking.finishFirstMove(); // first-move delay
	}
	
	

}

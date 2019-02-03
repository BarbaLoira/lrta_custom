package algorithms.lrtaDeeperLookahead;

import java.util.ArrayList;
import java.util.List;

import yaps.graph.Graph;
import yaps.simulator.multiagent.SimpleAgent;

public class Deeperlookahead extends SimpleAgent {
	private Graph graph;
	private int t = 0;
	public float executionConvergenceCost = 0;
	public float planingCost = 0;
	int profundidadeMax;
	List<Integer> resultVizinhos;
	public float firstTime = 0;

	public Deeperlookahead(Graph g, int meta, int profundidadeMax) {
		super(System.out);
		this.graph = g;
		UtilLookahead.graph = this.graph;
		UtilLookahead.meta = meta;
		this.profundidadeMax = profundidadeMax;
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
		if (t != UtilLookahead.meta) {
			UtilLookahead.startFirstMove();

			UtilLookahead.verificarCriacaoHeuristica(t);// verifica se necessita criar heuristica caso sim a heuristica
														// inicia
			// com
			// valor zero

			List<Integer> neighbors = graph.getSuccessors(t);
			// System.out.println(neighbors);

			proximoVizinho = deeperlookahead(t, neighbors, 0);

			UtilLookahead.setMaiorHeuristicaParaNodeAtual(t, proximoVizinho); // ht+1(st) â†? max{ ht(st), min(c(st, s)
																				// + ht(s)}

			performances(neighbors, proximoVizinho);

			return proximoVizinho;
		} else {
			return -1;
		}

	}

	private int deeperlookahead(int no, List<Integer> vizinhosProximos, int profundidade) {
		int idEscolhido = -1;
		if (profundidade == this.profundidadeMax) {
			int vizinho = UtilLookahead.getVizinhoMenorCustoHeuristica(no, vizinhosProximos);
			return UtilLookahead.custoAteVizinho(no, vizinho) + UtilLookahead.getValueHeuristica(vizinho);
		} else {
			int temp = -1;
			int aux;
			profundidade++;
			resultVizinhos = new ArrayList<>();
			for (Integer v : vizinhosProximos) {
				if (t != no) {
					return UtilLookahead.custoAteVizinho(no, v) + deeperlookahead(v, vizinhosProximos, profundidade);
				} else {
					aux = UtilLookahead.custoAteVizinho(no, v) + deeperlookahead(v, vizinhosProximos, profundidade);
					if (temp == -1 || aux < temp) {
						temp = aux;
						idEscolhido = v;
					}
				}
			}
		}

		return idEscolhido;
	}

	private void performances(List<Integer> neighbors, int proximoVizinho) {
		executionConvergenceCost += UtilLookahead.custoAteVizinho(t, proximoVizinho); // execution convergence cost
		UtilLookahead.planningCost(neighbors);
		planingCost = UtilLookahead.calcularTotalPlanningCost(); // planning cost
		firstTime = UtilLookahead.finishFirstMove(); // first-move delay
	}

}

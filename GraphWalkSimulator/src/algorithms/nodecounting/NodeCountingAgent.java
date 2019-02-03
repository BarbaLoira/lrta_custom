package algorithms.nodecounting;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import algorithms.lrta.Util;
import yaps.graph.Graph;
import yaps.simulator.multiagent.SimpleAgent;
import yaps.util.RandomUtil;

public class NodeCountingAgent extends SimpleAgent {
	private Graph graph;
	private int[] visitsOfNode;
	private int t = 0;

	public NodeCountingAgent(Graph g, int[] memory) {
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
		t = position.getCurrentNode();
		int proximoVizinho = 0;

		Util.verificarCriacaoHeuristica(t);// verifica se necessita criar heuristica caso sim a heuristica inicia com
											// valor zero

		List<Integer> neighbors = graph.getSuccessors(t);

		while (t != Util.meta) {
			proximoVizinho = Util.getVizinhoMenorCustoHeuristica(t, neighbors); // st+1 ← arg min (c(st, s) + ht(s))

			Util.setMaiorHeuristicaParaNodeAtual(t, proximoVizinho); // ht+1(st) ← max{ ht(st), min(c(st, s) + ht(s)}

		}
		// graph.getEdgeLength(proximoVizinho, Util.meta);
		// System.out.printf(" - next node: %d \n", nextNode);
		return proximoVizinho;
	}

}

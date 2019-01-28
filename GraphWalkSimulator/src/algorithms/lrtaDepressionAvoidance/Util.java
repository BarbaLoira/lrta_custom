package algorithms.lrtaDepressionAvoidance;

import java.util.ArrayList;
import java.util.List;
 
import yaps.graph.Graph;

public abstract class Util {
	public static List<Heuristica> heuristicas = new ArrayList<>();
	public static Graph graph;
	public static int meta;

	public static boolean verificarCriacaoHeuristica(int idNode) {
		if (!existeHeuristica(idNode)) {
			heuristicas.add(new Heuristica(idNode, 0));
			return true;
		}
		return false;
	}

	public static void setValueHeuristica(int idNode, int valueHeuristica) {
		for (Heuristica h : heuristicas) {
			if (h.nodeId == idNode)
				h.heuristica = valueHeuristica;
		}
	}

	public static int getValueHeuristica(int idNode) {
		for (Heuristica h : heuristicas) {
			if (h.nodeId == idNode)
				return h.heuristica;
		}
		return 0;
	}

	public static int custoAteVizinho(int nodeIdAtual, int nodeIdVizinho) {
		return graph.getEdgeLength(nodeIdAtual, nodeIdVizinho);

	}

	public static int heuristica(int nodeIdVizinho) {
		if (Util.verificarCriacaoHeuristica(nodeIdVizinho)) {
			return 0;
		} else {
			getValueHeuristica(nodeIdVizinho);
		}

		return 0;
	}

	public static int getVizinhoMenorCustoHeuristicaCustom2(int nodeIdAtual, List<Integer> neighbors) {
		int idMenorVizinho = 0, valorMenor = 1000, valorAux;
		for (Integer n : neighbors) {
			valorAux = heuristica(nodeIdAtual) - heuristica(n);
			if (valorMenor > valorAux) {
				idMenorVizinho = n;
				valorMenor = valorAux;
			}
		}

		return idMenorVizinho;
	}

	public static boolean existeHeuristica(int nodeId) {
		if (heuristicas.size() >= 0) {
			for (Heuristica h : heuristicas) {
				if (h.nodeId == nodeId)
					return true;
			}
		}
		return false;
	}

	 

	public static int calcularMinLearning(int firstNodeId, int t) {
		return getValueHeuristica(firstNodeId) - getValueHeuristica(t);

	}
	public static void setMaiorHeuristicaParaNodeAtual(int t, int proximoVizinho) {
		int minCustoHeuristica = custoAteVizinho(t, proximoVizinho) + getValueHeuristica(proximoVizinho);
		if (minCustoHeuristica > getValueHeuristica(t)) {
			setValueHeuristica(t, minCustoHeuristica);
		} else {
			setValueHeuristica(t, getValueHeuristica(t));
		}

	}
	
	public static int getVizinhoMenorCustoHeuristica(int nodeIdAtual, List<Integer> neighbors) {
		int idMenorVizinho = 0, valorMenor = 1000, valorAux;
		for (Integer n : neighbors) {
			valorAux = custoAteVizinho(nodeIdAtual, n) + heuristicaVizinho(n);
			if (valorMenor > valorAux) {
				idMenorVizinho = n;
				valorMenor = valorAux;
			}
		}

		return idMenorVizinho;
	}
	
	public static int heuristicaVizinho(int nodeIdVizinho) {
		if (Util.verificarCriacaoHeuristica(nodeIdVizinho)) {
			return 0;
		} else {
			getValueHeuristica(nodeIdVizinho);
		}

		return 0;
	}


}

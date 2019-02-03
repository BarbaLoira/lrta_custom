package algorithms.lrtaDeeperLookahead;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import repository.UtilResultados;
import yaps.graph.Graph;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import algorithms.lrta.Heuristica;
import repository.UtilResultados;
import yaps.graph.Graph;

public abstract class UtilLookahead {
	public static List<Heuristica> heuristicas;
	public static Graph graph;
	public static int meta;
	
	private static float estadosConsiderados=0;
	private static float quantidadeEscolha=0; 
	private static Calendar startFirstMove;
	
	public static boolean verificarCriacaoHeuristica(int idNode) { 
		if(heuristicas == null) {
			heuristicas = UtilResultados.heuristicas;
		}
		if (!existeHeuristica(idNode)) {
			Heuristica h = new Heuristica(idNode, 0);
			heuristicas.add(h);
			// System.out.println(h.nodeId + " " + h.heuristica);
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
		int result = 0;
		for (Heuristica h : heuristicas) {
			if (h.nodeId == idNode)
				result = h.heuristica;
		}
		return result;
	}

	public static int custoAteVizinho(int nodeIdAtual, int nodeIdVizinho) {
		// System.out.println(graph);
		int result = graph.getEdgeLength(nodeIdAtual, nodeIdVizinho);

		return ((result < 0) ? -result : result);
	}

	public static int heuristicaVizinho(int nodeIdVizinho) {
		if (UtilLookahead.verificarCriacaoHeuristica(nodeIdVizinho)) {
			return 0;
		} else {
			getValueHeuristica(nodeIdVizinho);
		}
		return 0;
	}

	public static int getVizinhoMenorCustoHeuristica(int nodeIdAtual, List<Integer> neighbors) {
		int idMenorVizinho = 0, aux = -1, valorVizinho;
	//	System.out.println("no atual :" + nodeIdAtual + " heuris " + getValueHeuristica(nodeIdAtual));
		for (int x = 0; x < neighbors.size(); x++) {

			int custo = custoAteVizinho(nodeIdAtual, neighbors.get(x));
			int heuris = getValueHeuristica(neighbors.get(x));
			valorVizinho = custo + heuris;
			//System.out.println("\n Vizinho :" + neighbors.get(x) + " custo " + custo + " heuris " + heuris
			//		+ "valor total " + valorVizinho);

			/*
			 * System.out.println( "\n Vizinho :" + neighbors.get(x) + " custo " +
			 * custoAteVizinho(nodeIdAtual, neighbors.get(x)) + " heuris " +
			 * getValueHeuristica(neighbors.get(x)));
			 * 
			 * valorVizinho = custoAteVizinho(nodeIdAtual, neighbors.get(x)) +
			 * heuristicaVizinho(neighbors.get(x));
			 */
			if (aux == -1) {
				idMenorVizinho = neighbors.get(x);
				aux = valorVizinho;
			} else if (aux > valorVizinho) {

			//	System.out.println("idAux " + idMenorVizinho + " aux  " + aux + " > " + "vizinho " + neighbors.get(x)
			//			+ " valorVizinho " + valorVizinho);

				idMenorVizinho = neighbors.get(x);
				aux = valorVizinho;
			}

		}

		// snapShootVizinhosComEscolhido(neighbors, idMenorVizinho);

		return idMenorVizinho;
	}

	private static void snapShootVizinhosComEscolhido(List<Integer> neighbors, int idMenorVizinho) {
		for (Integer n : neighbors) {
			System.out.println("\nSnapshoot");
			if (idMenorVizinho == n) {
				System.out.println("id do escolhido : " + n + " valor heuris " + getValueHeuristica(n));
			} else {
				System.out.println("id  : " + n + " valor heuris " + getValueHeuristica(n));
			}

		}

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

	public static void setMaiorHeuristicaParaNodeAtual(int t, int proximoVizinho) {

		int custo = custoAteVizinho(t, proximoVizinho);
		int heuristca = getValueHeuristica(proximoVizinho);

		setValueHeuristica(t, custo + heuristca);
	}
	/*
	 * int minCustoHeuristica = custoAteVizinho(t, proximoVizinho) +
	 * getValueHeuristica(proximoVizinho); // System.out.println( custoAteVizinho(t,
	 * proximoVizinho) +" "+ // getValueHeuristica(proximoVizinho)); if
	 * (minCustoHeuristica > getValueHeuristica(t)) { setValueHeuristica(t,
	 * minCustoHeuristica);
	 * 
	 * }
	 */

	public static void planningCost(List<Integer> neighbors) {
		 estadosConsiderados += neighbors.size();
		quantidadeEscolha++;
	}
	
	public static float calcularTotalPlanningCost() {  
		return estadosConsiderados / quantidadeEscolha;
	}
	public static void startFirstMove() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		startFirstMove = calendar;

	}

	public static float finishFirstMove() {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);

		return (calendar.getTimeInMillis() - startFirstMove.getTimeInMillis());
	}

}

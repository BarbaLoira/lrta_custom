package algorithms.lrta_star;

import java.util.LinkedList;
import java.util.List;

import yaps.graph.Edge;
import yaps.graph.Graph;
import yaps.simulator.multiagent.SimpleAgent;
import yaps.util.RandomUtil;

public class NodeCountingAgent extends SimpleAgent {
	private Graph graph;
	private int[] visitsOfNode;
	private int[] heuristic; // h
	private int currentStage; // t

	public NodeCountingAgent(Graph g, int[] memory) {
		super(System.out);
		this.graph = g;
		this.visitsOfNode = memory;
	}

	@Override
	public void onStart() {
		// nop
	}

	@Override
	public void onTurn(int nextTurn) {
		// nop

	}

	@Override
	public int onArrivalInNode(int nextTurn) {
		int currentNode = position.getCurrentNode();
		// System.out.printf("[Agent %d] TURN %d, in node %d \n", this.getIdentifier(),
		// nextTurn, currentNode);

		visitedNode(currentNode);

		List<Integer> nodesWithMinVisits = returnNeighborsOrderByMinCosts(currentNode);

		// System.out.printf(" - minimum visits: %d (neighbors: %s) \n", minVisits,
		// nodesWithMinVisits);

		int nextNode = RandomUtil.chooseAtRandom(nodesWithMinVisits);

		// System.out.printf(" - next node: %d \n", nextNode);
		return nextNode;
	}

	private List<Integer> returnNeighborsOrderByMinVisits(int currentNode) {
		List<Integer> neighbors = graph.getSuccessors(currentNode);

		int minVisits = Integer.MAX_VALUE;
		List<Integer> nodesWithMinVisits = new LinkedList<Integer>();

		for (Integer nodeX : neighbors) {
			if (visitsOfNode[nodeX] < minVisits) {
				minVisits = visitsOfNode[nodeX];
				nodesWithMinVisits.clear();
				nodesWithMinVisits.add(nodeX);
			} else if (visitsOfNode[nodeX] == minVisits) {
				nodesWithMinVisits.add(nodeX);
			}

		}
		return nodesWithMinVisits;
	}

	private List<Integer> returnNeighborsOrderByMinCosts(int currentNode) {
		List<Integer> neighbors = graph.getSuccessors(currentNode);

		int minVisits = Integer.MAX_VALUE;
		List<Integer> nodesWithMinVisits = new LinkedList<Integer>();

		
		for (Integer nodeX : neighbors) {
			Edge e = graph.getEdge(currentNode, nodeX);
		 
			
			if (visitsOfNode[nodeX] < minVisits) {
				minVisits = visitsOfNode[nodeX];
				nodesWithMinVisits.clear();
				nodesWithMinVisits.add(nodeX);
			} else if (visitsOfNode[nodeX] == minVisits) {
				nodesWithMinVisits.add(nodeX);
			}

		}

		return nodesWithMinVisits;

	}

	private void visitedNode(int currentNode) {
		visitsOfNode[currentNode] = visitsOfNode[currentNode] + 1;
	}

}

package algorithms.nodecounting;

import java.util.LinkedList;
import java.util.List;

import yaps.graph.Graph;
import yaps.simulator.multiagent.SimpleAgent;
import yaps.util.RandomUtil;

public class NodeCountingAgent extends SimpleAgent {
	private Graph graph;
	private int[] visitsOfNode;

	public NodeCountingAgent(Graph g, int[] memory){
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
		
		visitsOfNode[currentNode] = visitsOfNode[currentNode] + 1;
		
		List<Integer> neighbors = graph.getSuccessors(currentNode);
		
		int minVisits = Integer.MAX_VALUE;
		List<Integer> nodesWithMinVisits = new LinkedList<Integer>();
		
		for (Integer nodeX : neighbors){
			if (visitsOfNode[nodeX] < minVisits){
				minVisits = visitsOfNode[nodeX];
				nodesWithMinVisits.clear();
				nodesWithMinVisits.add(nodeX);
			} else if (visitsOfNode[nodeX] == minVisits){
				nodesWithMinVisits.add(nodeX);
			}
			
		}
		
		//System.out.printf(" - minimum visits: %d (neighbors: %s) \n", minVisits, nodesWithMinVisits);
		
		int nextNode = RandomUtil.chooseAtRandom(nodesWithMinVisits);
		
	 System.out.printf(" - next node: %d \n", nextNode);		
		return nextNode;
	}

}

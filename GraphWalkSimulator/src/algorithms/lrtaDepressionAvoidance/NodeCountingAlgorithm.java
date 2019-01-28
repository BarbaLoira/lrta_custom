package algorithms.lrtaDepressionAvoidance;

import yaps.graph.Graph;
import yaps.simulator.core.AgentPosition;
import yaps.simulator.multiagent.SimpleAgent;
import yaps.simulator.multiagent.SimpleMultiagentAlgorithm;


public class NodeCountingAlgorithm extends SimpleMultiagentAlgorithm {
	
	private int[] sharedMemory;  // mapeamento  "id do vertice" (usado como indice) -> "valor do vértice"

	public NodeCountingAlgorithm(){
		super("Node Count");
	}
	
	@Override
	public void onSimulationEnd() {
		//does nothing
	}

	@Override
	public SimpleAgent[] createTeam(AgentPosition[] positions, Graph g, int time) {
		int numAgents = positions.length;
		
		sharedMemory = new int[g.getNumNodes()]; //starts all zero
		
		SimpleAgent[] agents = new SimpleAgent[numAgents];
		for (int i = 0; i < numAgents; i++) {
			agents[i] = new LrtaCustom2Agent(g, sharedMemory);
		}
		return agents;
	}

}

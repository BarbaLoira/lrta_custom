package algorithms.lrta;

import yaps.graph.Graph;
import yaps.simulator.core.AgentPosition;
import yaps.simulator.multiagent.SimpleAgent;
import yaps.simulator.multiagent.SimpleMultiagentAlgorithm;

public class LrtaAlgorithm extends SimpleMultiagentAlgorithm {

	private int[] sharedMemory; // mapeamento "id do vertice" (usado como indice) -> "valor do vértice"
	private int meta = 15;
	SimpleAgent[] agents;
	Graph g;
	int numAgents;

	public LrtaAlgorithm() {
		super("Node Count");
	}

	@Override
	public void onSimulationEnd() {
		for (int i = 0; i < numAgents; i++) {
			System.out.println(((LrtaAgent) agents[i]).executionConvergenceCost);
			System.out.println(((LrtaAgent) agents[i]).planingCost);
		}
		// does nothing
	}

	@Override
	public SimpleAgent[] createTeam(AgentPosition[] positions, Graph g, int time) {
		this.g = g;
		// System.out.println("createTeam");
		numAgents = positions.length;

		sharedMemory = new int[g.getNumNodes()]; // starts all zero

		agents = new SimpleAgent[numAgents];
		for (int i = 0; i < numAgents; i++) {
			agents[i] = new LrtaAgent(g, sharedMemory, meta);
		}
		// System.out.println("leave createTeam");
		return agents;
	}

}

package algorithms.lrta;

import yaps.graph.Graph;
import yaps.simulator.core.AgentPosition;
import yaps.simulator.multiagent.SimpleAgent;
import yaps.simulator.multiagent.SimpleMultiagentAlgorithm;

public class LrtaAlgorithm extends SimpleMultiagentAlgorithm {
	private int meta;
	SimpleAgent[] agents;
	Graph g;
	int numAgents;
	float firstTime = -1;

	public LrtaAlgorithm(int meta) {
		super("Node Count");
		this.meta = meta;
	}

	@Override
	public void onSimulationEnd() {
		for (int i = 0; i < numAgents; i++) {
			System.out.println("Agente " + (i + 1));
			if (firstTime == -1) {
				firstTime = ((LrtaAgent) agents[i]).firstTime;
				System.out.println("first-move delay (lag) " + firstTime);
			}
			System.out.println("Custo de convergencia " + ((LrtaAgent) agents[i]).executionConvergenceCost);
			System.out.println("Custo de planejamento " + ((LrtaAgent) agents[i]).planingCost);

		}
		// does nothing
	}

	@Override
	public SimpleAgent[] createTeam(AgentPosition[] positions, Graph g, int time) {
		this.g = g;
		// System.out.println("createTeam");
		numAgents = positions.length;

		agents = new SimpleAgent[numAgents];
		for (int i = 0; i < numAgents; i++) {
			agents[i] = new LrtaAgent(g, meta);
		}
		// System.out.println("leave createTeam");
		return agents;
	}
}

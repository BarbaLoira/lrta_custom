package algorithms.lrtaDeeperLookahead;

import algorithms.lrta.LrtaAgent;
import yaps.graph.Graph;
import yaps.simulator.core.AgentPosition;
import yaps.simulator.multiagent.SimpleAgent;
import yaps.simulator.multiagent.SimpleMultiagentAlgorithm;

public class DeeperLookaheadAlgorithm extends SimpleMultiagentAlgorithm {
	private int meta;
	SimpleAgent[] agents;
	Graph g;
	int numAgents;
	int profundidadeMax;
	float firstTime = -1;

	public DeeperLookaheadAlgorithm(int meta, int profundidadeMax) {
		super("Node Count");
		this.meta = meta;
		this.profundidadeMax = profundidadeMax;
	}

	@Override
	public void onSimulationEnd() {
		for (int i = 0; i < numAgents; i++) {
			System.out.println("Agente " + (i + 1));
			if (firstTime == -1) {
				firstTime = ((Deeperlookahead) agents[i]).firstTime;
				System.out.println("first-move delay (lag) " + firstTime);
			}
			System.out.println("Custo de convergencia " + ((Deeperlookahead) agents[i]).executionConvergenceCost);
			System.out.println("Custo de planejamento " + ((Deeperlookahead) agents[i]).planingCost);
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
			agents[i] = new Deeperlookahead(g, meta, profundidadeMax);
		}
		// System.out.println("leave createTeam");
		return agents;
	}
}

package research;

import peersim.core.*;

import java.util.ArrayList;

import peersim.config.*;

public class CuckooSearch implements Control {
	private static final String PAR_MAXGENERATION = "generation";
	private static int maxGeneration;

	public CuckooSearch(String prefix) {
		maxGeneration = Configuration.getInt(prefix + "." + PAR_MAXGENERATION);
	}

	public static Node search() {

		NestSet ns = new NestSet();
		for (int generation = 0; generation < maxGeneration; generation++) {
			// System.out.println("Generation: " + generation);
			ns.alternate();
		}
		Node bestNode = ns.getBestNode();

		return bestNode;
	}

	public static Node search(Data data) {
		NestSet ns = new NestSet();
		for (int generation = 0; generation < maxGeneration; generation++) {
			// System.out.println("Generation: " + generation);
			ns.alternate();
			// ArrayList<Nest> nest = ns.getNestSet();
			// System.out.println("NOWNEST:");
			// for (int k = 0; k < 10; k++) {
			// System.out.printf("\t%d: ", k);
			// System.out.println("Node: " + nest.get(k).getNode().getIndex() + " value " +
			// nest.get(k).getValue()
			// + " (" + nest.get(k).egg[0] + ", " + nest.get(k).egg[1] + ")");
			// }
		}

		Node bestNode = null;
		int nestNum = 0;
		while (nestNum < ns.getNestSize()) {
			bestNode = ns.getBestNode(nestNum);
			Storage storage = SharedResource.getNodeStorage("cuckoo", bestNode);
			Parameter parameter = SharedResource.getNodeParameter("cuckoo", bestNode);
			int capacity = parameter.getCapacity();
			int occupancy = data.getSize();
			int newCapacity = capacity - occupancy;

			if (!storage.contains(data) && newCapacity >= 0) {
				break;
			}
			// System.out.println("Now Nest Num: " + nestNum);
			nestNum++;
		}

		// System.out.println("return Node:" + bestNode.getIndex());
		return bestNode;
	}

	public boolean execute() {
		return false;
	}

}
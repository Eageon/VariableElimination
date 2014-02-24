import java.util.ArrayList;
import java.util.LinkedList;


public class Variable implements Comparable<Variable> {
	int d;
	boolean isEvdence;
	int value;
	
	int index = -1;
	
	LinkedList<Variable> parents = new LinkedList<>();
	LinkedList<Variable> children = new LinkedList<>();
	LinkedList<Integer> neighbors = new LinkedList<>();
	
	public Variable(int domainSize) {
		d = domainSize;
		isEvdence = false;
		value = -1;
	}
	
	public int domainSize() {
		return d;
	}
	
	public void setEvidence(int observedValue) {
		isEvdence = true;
		value = observedValue;
	}
	
	public int degree() {
		return neighbors.size();
	}

	@Override
	public int compareTo(Variable o) {
		// TODO Auto-generated method stub
		return this.degree() - o.degree();
	}
	
	public void addParent(Variable par) {
		parents.add(par);
	}
	
	public void addChild(Variable child) {
		children.add(child);
	}
	
	public void addNeighbor(int neigh) {
		neighbors.add(neigh);
	}
	
	public void removeNeighbor(Integer nei) {
		neighbors.remove(nei);
	}
	
	public void destroyVariableInGraph(ArrayList<Variable> graph) {
		for(Integer n : neighbors) {
			Variable nei = graph.get(n);
			nei.removeNeighbor(index);
		}
		neighbors = null;
	}
	
	public boolean isAdjacent(int var) {
		for(Integer nei : neighbors) {
			if(var == nei)
				return true;
		}
		
		return false;
	}
}

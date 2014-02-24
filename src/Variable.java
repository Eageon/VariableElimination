import java.util.LinkedList;


public class Variable implements Comparable<Variable> {
	int d;
	boolean isEvdence;
	int value;
	
	LinkedList<Variable> parents = new LinkedList<>();
	LinkedList<Variable> children = new LinkedList<>();
	LinkedList<Variable> neighbors = new LinkedList<>();
	
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
	
	public void addNeighbor(Variable neigh) {
		neighbors.add(neigh);
	}
	
	public void destroyGraph() {
		for(Variable n : neighbors) {
			n.neighbors.remove(this);
		}
		neighbors = null;
	}
	
	public boolean isAdjacent(Variable var) {
		for(Variable nei : neighbors) {
			if(var == nei)
				return true;
		}
		
		return false;
	}
}

import java.util.ArrayList;


public class Factor implements Comparable<Factor>{
	ArrayList<Variable> variables;
	ArrayList<Double> table;
	
	@Override
	public int compareTo(Factor o) {
		// TODO Auto-generated method stub
		return this.variables.size() - o.variables.size();
	}
	
	public int degree() {
		return variables.size();
	}
	
	public Factor(int numScopes) {
		if(0 > numScopes)
			return;
		
		variables = new ArrayList<>(numScopes);
		table = new ArrayList<>(numScopes);
	}
	
	public Variable getVariable(int index) {
		if(0 > index)
			return null;
		
		return variables.get(index);
	}
	
	public void setVariable(int index, Variable variable) {
		if(0 > index)
			return;
		
		variables.set(index, variable);
	}
	
	public double getTabelValue(int index) {
		if(0 > index)
			return -1.0;
			
		return table.get(index);
	}
	
	public void setTableValue(int index, double value) {
		if(0 > index || 0.0 > value)
			return;
		
		table.set(index, value);
	}
}

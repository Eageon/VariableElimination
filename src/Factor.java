import java.util.ArrayList;

public class Factor {
	ArrayList<Variable> variables = null;
	ArrayList<Double> table = null;

	int index = -1;
	
	public int numColomns() {
		return variables.get(variables.size() - 1).domainSize();
	}
	
	public int numScopes() {
		return variables.size();
	}

	public Factor(int numScopes) {
		if (0 > numScopes)
			return;

		variables = new ArrayList<>(numScopes);
	}
	
	public Factor(ArrayList<Variable> vars) {
		variables = vars;
	}

	public void initTable(int num) {
		if (0 > num)
			return;

		table = new ArrayList<>(num);
	}

	public Variable getVariable(int index) {
		if (0 > index)
			return null;

		return variables.get(index);
	}

	public void setVariable(int index, Variable variable) {
		if (0 > index)
			return;
		if (index == variables.size())
			variables.add(variable);
		else
			variables.set(index, variable);
		variable.addMentionFactor(this);
	}
	
	public void setGraph() {
		Variable var = variables.get(variables.size() - 1);
		
		for(int i = 0; i < variables.size() - 1; i++) {
			Variable par = variables.get(i);
			var.addNeighbor(par.index);
			par.addNeighbor(var.index);
		}
	}

	public double getTabelValue(int index) {
		if (0 > index)
			return -1.0;

		return table.get(index);
	}

	public void setTableValue(int index, double value) {
		if (0 > index || 0.0 > value)
			return;

		if (index == table.size())
			table.add(value);
		else
			table.set(index, value);
	}
	
	/**
	 * var i in the scope
	 * @param var
	 * @return whether the var is in the scope of this factor
	 */
	public boolean inScope(Variable var) {
		for(Variable v : variables) {
			if(var == v)
				return true;
		}
		
		return false;
	}
	
	/**
	 * 
	 * @param index of table
	 * @return the set of the values of correspanding variables
	 */
	public int[] tableIndexToVaraibleValue(int index) {
		int[] values = new int[variables.size()];
		
		int denum = table.size();
		
		for (int i = 0; i < values.length; i++) {
			denum /= variables.get(i).domainSize();
			int trueValue = index / denum;
			index %= denum;
			values[i] = trueValue;
		}
		
		return values;
	}
	
	/**
	 * do reversely with tableIndexToVaraibleValue
	 * @param values the indices of values of variables
	 * @return
	 */
	public int variableValueToTableIndex(int[] values) {
		int multi = 1;
		int index = 0;
		
		for (int i = values.length - 1; i >= 0; i++) {
			index += values[i] * multi;
			multi *= variables.get(i).domainSize();
		}
		
		return index;
	}
	
	public void instantiateVariable(Variable var, int value) {
		
		int indexInFactor = -1;
		// find the index in the factor.
		for (int i = 0; i < variables.size(); i++) {
			if(var == variables.get(i)) {
				indexInFactor = i;
				break;
			}
		}
		
		if(indexInFactor < 0)
			return;
		
		for (int i = 0; i < table.size(); i++) {
			int[] valuesArray = tableIndexToVaraibleValue(i);
			if(valuesArray[indexInFactor] != value)
				setTableValue(i, 0.0);
		}
	}
}

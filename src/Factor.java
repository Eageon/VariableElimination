import java.util.ArrayList;

public class Factor implements Comparable<Factor> {
	ArrayList<Variable> variables = null;
	ArrayList<Double> table = null;

	@Override
	public int compareTo(Factor o) {
		// TODO Auto-generated method stub
		return this.variables.size() - o.variables.size();
	}

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
}

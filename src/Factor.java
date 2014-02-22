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
}

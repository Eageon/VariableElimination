import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.*;


public class Eliminator {
	ArrayList<Variable> variablesToEliminated;
	
	ArrayList<Factor> factors; // refer to factors
	ArrayList<Variable> variables; // refer to variables
	LinkedList<Variable> order;
	
	ArrayList<Integer>  orderOfIndex;  // to do
	
	class OrderComparator implements Comparator<Variable> {

		@Override
		public int compare(Variable o1, Variable o2) {
			// TODO Auto-generated method stub
			return order.indexOf(o1) - order.indexOf(o2);
		}
	}
	
	private void sortVaraiblesToElminatedByOrdering() {
		Collections.sort(variablesToEliminated, new OrderComparator());
	}
	
	public Factor Sum_Product_VE() {
		sortVaraiblesToElminatedByOrdering();
		
		for (int i = 0; i < variablesToEliminated.size(); i++) {
			factors = Sum_Product_Eliminate_Var(variablesToEliminated.get(i));
		}
		
		return Product(factors);
	}

	private ArrayList<Factor> Sum_Product_Eliminate_Var(Variable var) {
		ArrayList<Factor> factorPrime = new ArrayList<>();
		
		for(Factor factor : factors) {
			if(factor.inScope(var)) {
				factorPrime.add(factor);
			}
		}
		
		ArrayList<Factor> factorPrimePrime = new ArrayList<>();
		
		for(Factor factor : factors) {
			if(!factorPrime.contains(factor)) {
				factorPrimePrime.add(factor);
			}
		}
		
		Factor bigFi = Product(factorPrime);
		Factor tao = SumOut(bigFi, var);
		
		factorPrimePrime.add(tao);
		
		return factorPrimePrime;
	}
	
	private Factor Product(ArrayList<Factor> factorPrime) {
		HashSet<Variable> set = new HashSet<>();
		
		for(Factor factor : factorPrime) {
			for(Variable var : factor.variables) {
				set.add(var);
			}
		}
		
		ArrayList<Variable> Z = new ArrayList<>(set.size());
		int numTables = 1;
		for(Variable var : set) {
			Z.add(var);
			numTables *= var.domainSize();
		}
		
		Factor fRet = new Factor(Z.size());
		fRet.initTable(numTables);
		for (int i = 0; i < fRet.table.size(); i++) {
			fRet.table.set(i, 1.0);
		}
		
		// for each instantiation
		for (int z = 0; z < fRet.table.size(); z++) {
			for (int i = 0; i < factorPrime.size(); i++) {
				
			}
		}
		
		return null;
	}
	
	private Factor SumOut(Factor bigFi, Variable var) {
		
		return null;
	}
}

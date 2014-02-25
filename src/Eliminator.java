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
		LinkedList<Variable> set = new LinkedList<>();
		
		for(Factor factor : factorPrime) {
			for(Variable var : factor.variables) {
				if(!set.contains(var))
					set.add(var);
			}
		}
		
		ArrayList<Variable> Z = new ArrayList<>(set.size());
		int numTables = 1;
		for(Variable var : set) {
			Z.add(var);
			numTables *= var.domainSize();
		}
		
		Factor fRet = new Factor(Z);
		fRet.initTable(numTables);
		for (int i = 0; i < fRet.table.size(); i++) {
			fRet.table.set(i, 1.0);
		}
		
		
		// for each instantiation
		for (int z = 0; z < fRet.table.size(); z++) {
			//Factor zFactor = factorPrime.get(z);
			int[] zValueIndex = fRet.tableIndexToVaraibleValue(z);
			
			for (int i = 0; i < factorPrime.size(); i++) {
				Factor xFactor = factorPrime.get(i);
				int[] primeValues = new int[xFactor.numScopes()];
				// find the same variable
				for (int j = 0; j < primeValues.length; j++) {
					for (int m = 0; m < zValueIndex.length; m++) {
						if(fRet.variables.get(m) == xFactor.variables.get(j)) {
							primeValues[j] = zValueIndex[m];
							break; // need to improve
						}
					}
				}
				
				int xTableIndex = xFactor.variableValueToTableIndex(primeValues);
				// the multiply two double;
				fRet.table.set(z, fRet.table.get(z) * xFactor.table.get(xTableIndex));
			}
		}
		
		return fRet;
	}
	
	private double xConsistentWithZ(ArrayList<Factor> z, int[] zValueIndex) {
		for (int i = 0; i < zValueIndex.length; i++) {
			
		}
		
		return 0.0;
	}
	
	private Factor SumOut(Factor bigFi, Variable var) {
		
		return null;
	}
}

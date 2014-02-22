import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class GraphicalModel {
	int numVariables;
	ArrayList<Factor> factors;
	
	ArrayList<Variable> variables;

	public GraphicalModel(String fileName) {
		buildModel(fileName);
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
	
	public Factor getFactor(int index) {
		if(0 > index)
			return null;
		
		return factors.get(index);
	}
	
	public void setFactor(int index, Factor factor) {
		if(0 > index)
			return;
		
		factors.set(index, factor);
	}
	
	private void buildMarkovNetwork(BufferedReader reader) throws NumberFormatException, IOException {
		int size = Integer.valueOf(reader.readLine());
		numVariables = size;
		variables = new ArrayList<>(size);
		
		String line3 = reader.readLine();
		String[] domains = line3.split(" ");
		
		for(String s : domains) {
			int domainSize = Integer.valueOf(s);
			Variable v = new Variable(domainSize);
			variables.add(v);
		}
		
		String line4 = reader.readLine();
		int numFunctions = Integer.valueOf(line4);
		factors = new ArrayList<>(numFunctions);
		
		String funcLine = null;
		while(null != (funcLine = reader.readLine()) || !funcLine.equals("")) {
			String[] args = funcLine.split(" ");
			if(2 > args.length)
				continue;
			int numScopes = Integer.valueOf(args[0]);
			Factor factor = new Factor(numScopes);
			
			for(int i = 1; i < args.length; i++) {
				int indexVaraible = Integer.valueOf(args[i]);
				factor.setVariable(i - 1, this.getVariable(indexVaraible));
			}
		}
		
		if(null == funcLine) {
			System.out.println("File Format problem");
			System.exit(-1);
		}
		
		
	}
	
	private void buildBayesianNetwork(BufferedReader reader) {
		
	}

	public void buildModel(String fileName) {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			System.out.println("Can NOT find file " + fileName);
			System.exit(-1);
		}

		try {
			String line = null;
			String network = reader.readLine();
			if (network.equals("MARKOV")) {
				buildMarkovNetwork(reader);
			} else if (network.equals("BAYES")) {
				buildBayesianNetwork(reader);
			} else {
				System.out.println("Wrong preamble " + network);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

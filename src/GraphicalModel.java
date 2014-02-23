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
		if (0 > index)
			return null;

		return variables.get(index);
	}

	public void setVariable(int index, Variable variable) {
		if (0 > index)
			return;

		variables.set(index, variable);
	}

	public Factor getFactor(int index) {
		if (0 > index)
			return null;

		return factors.get(index);
	}

	public void setFactor(int index, Factor factor) {
		if (0 > index)
			return;

		factors.set(index, factor);
	}

	@SuppressWarnings({ "null", "unused" })
	private void buildMarkovNetwork(BufferedReader reader)
			throws NumberFormatException, IOException {
		int size = Integer.valueOf(reader.readLine());
		numVariables = size;
		variables = new ArrayList<>(size);

		String line3 = reader.readLine();
		String[] domains = line3.split(" ");

		// set variables
		for (String s : domains) {
			int domainSize = Integer.valueOf(s);
			Variable v = new Variable(domainSize);
			variables.add(v);
		}

		String line4 = reader.readLine();
		int numFunctions = Integer.valueOf(line4);
		factors = new ArrayList<>(numFunctions);

		// initialize factors without setting the table
		String funcLine = null;
		while (null != (funcLine = reader.readLine()) && !funcLine.equals("")) {
			String[] args = funcLine.split(" ");
			if (2 > args.length)
				continue;
			int numScopes = Integer.valueOf(args[0]);
			Factor factor = new Factor(numScopes);

			for (int i = 1; i < args.length; i++) {
				int indexVaraible = Integer.valueOf(args[i]);
				factor.setVariable(i - 1, this.getVariable(indexVaraible));
			}
		}

		if (null == funcLine) {
			System.out.println("File Format problem");
			System.exit(-1);
		}

		// Then set CPT
	}

	private void buildBayesianNetwork(BufferedReader reader)
			throws NumberFormatException, IOException {
		int size = Integer.valueOf(reader.readLine());
		numVariables = size;
		variables = new ArrayList<>(size);

		String line3 = reader.readLine();
		String[] domains = line3.split(" ");

		// set variables
		for (String s : domains) {
			int domainSize = Integer.valueOf(s);
			Variable v = new Variable(domainSize);
			variables.add(v);
		}

		String line4 = reader.readLine();
		int numFactors = Integer.valueOf(line4);
		factors = new ArrayList<>(numFactors);

		// initialize factors without setting the table
		String factorLine = null;
		while (null != (factorLine = reader.readLine())
				&& !factorLine.equals("")) {
			String[] args = factorLine.split("\t| ");
			if (2 > args.length)
				continue;
			int indexLastVariable = Integer.valueOf(args[args.length - 1]);
			int numScopes = Integer.valueOf(args[0]);
			//variables.get(indexLastVariable).domainSize();
			Factor factor = new Factor(numScopes);
			factors.add(factor);
			

			for (int i = 1; i < args.length; i++) {
				int indexVaraible = Integer.valueOf(args[i]);
				factor.setVariable(i - 1, this.getVariable(indexVaraible));
			}
		}

		if (null == factorLine) {
			System.out.println("File Format problem");
			System.exit(-1);
		}

		// Then set CPT
		int indexFactor = 0;
		String head = null;
		while (null != (head = reader.readLine())) {
			int numCells = Integer.valueOf(head);
			
			Factor factor = factors.get(indexFactor++);
			factor.initTable(numCells);
			
			int numLinesToRead = numCells / factor.numColomns();
			

			String tableRow = null;
			int i = 0;
			for (i = 0; i < numLinesToRead; i++) {
				tableRow = reader.readLine();
				if (null == tableRow || tableRow.equals(""))
					break;

				tableRow = tableRow.trim();
				String[] tableRowValues = tableRow.split("\t| ");
				if (tableRowValues.length != factor.numColomns()) {
					System.out.println("Factor row format error");
					System.exit(-1);
				}

				for (int j = 0; j < tableRowValues.length; j++) {
					factor.setTableValue(i * factor.numColomns() + j,
							Double.valueOf(tableRowValues[j]));
				}
			}
			
			// judge the validity of the file
			if(i != numLinesToRead) {
				System.out.println("Factor " + i + ": wrong CPT format");
				System.exit(-1);
			}
			tableRow = reader.readLine();
			if(!tableRow.equals("")) {
				System.out.println("CPT Not separated by new line");
				System.exit(-1);
			}
		}
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
	
	public static void main(String[] args) {
		String fileName = "/home/leageon/GraphicalModel/hw2-problems/BN_8.uai";
		
		GraphicalModel model = new GraphicalModel(fileName);
		System.out.println("Succeed!");
	}
}

package de.swm.rxjava.tutorial02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



/**
 * Created by xie.fei on 12.04.2016.
 */
public class Driver {
	public static void main(String[] args) throws IOException {
		List<Avariable> variables = Collections.synchronizedList(new ArrayList<Avariable>());
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		Avariable avariable = null;
		String varName = null;
		Double varValue = 0.0;
		while (true) {
			System.out.println("please enter var name:");
			varName = bufferedReader.readLine();
			if (varName.equals("exit")) {
				break;
			} else if (varName.trim().isEmpty()) {
				continue;
			}
			System.out.println("please enter var value");
			varValue = Double.valueOf(bufferedReader.readLine());
			avariable = new Avariable(varValue, varName);
			if (!variables.contains(avariable)) {
				variables.add(avariable);
			}
			System.out.println("added variable: " + variables.stream().mapToDouble(x -> x.getVarValue()).sum());
		}

	}
}

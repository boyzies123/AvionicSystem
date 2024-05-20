/*
 * Code made by: Harry Booth-Beach
 * Date created: 20/05/2024
 * Date modified: 20/05/2024
 */
package code.simulator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Simulator {
    private List<String> fieldNames;

    public Simulator() {
        fieldNames = new ArrayList<>();
    }

    /**
     * Method for reading CSV files
     * @param fileName
     * @param sim
     * @return
     */
    public static ArrayList<Instance> readData(String fileName, Simulator sim) {
		ArrayList<Instance> instances = new ArrayList<>();
		try {
			Scanner sc = new Scanner(new File(fileName));
			sc.useDelimiter(",|\\n");
			// save field names
            sim.fieldNames = Arrays.asList(sc.nextLine().split(","));
            
			// parse data
			while (sc.hasNext()) {
                int id = 0;
				String[] values = sc.nextLine().split(",");
                HashMap<String, Double> fields = new HashMap<>();
				for (int i=0; i<sim.fieldNames.size(); i++) {
					fields.put(sim.fieldNames.get(i), Double.parseDouble(values[i]));
				}
				instances.add(new Instance(id, fields));
                id++;
			}
			sc.close();
		} catch (FileNotFoundException e) {
			System.err.println("File not found: " + e.getMessage());
		}
		return instances;
	}

    /**
     * Main method for running the simulator
     * @param args
     */
    public static void main(String[] args) {
        Simulator sim = new Simulator();
        // take airspeed sensor data

        // take altitude sensor data

        // take attitude sensor data

        // take engine sensor data


    }
}
